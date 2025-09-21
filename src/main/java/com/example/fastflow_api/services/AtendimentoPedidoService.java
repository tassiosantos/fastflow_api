package com.example.fastflow_api.services;

import com.example.fastflow_api.dtos.QueueBatchUpdateEventDTO;
import com.example.fastflow_api.models.entities.Atendimento;
import com.example.fastflow_api.models.entities.PedidoPaciente;
import com.example.fastflow_api.repositories.PedidoPacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class AtendimentoPedidoService {

    @Autowired
    PedidoPacienteService pedidoPacienteService;

    @Autowired
    PedidoPacienteRepository pedidoPacienteRepository;

    @Autowired
    WebhookDispatcherService webhookDispatcherService;





    @Transactional
    public void checkInPedido(String pedidoId) {

        pedidoPacienteService.checkInPedido(pedidoId);
        this.recalcularFilaAtendimento();
    }


    public void recalcularFilaAtendimento() {
        List<PedidoPaciente> pedidosCheckedIn = pedidoPacienteRepository.findAllByCheckedInTrue();
        if (pedidosCheckedIn.isEmpty()) return;

        // 1) Agrupar participações por Atendimento
        Map<UUID, List<PedidoPaciente>> pedidosPorAtendimento = new HashMap<>();
        for (PedidoPaciente pp : pedidosCheckedIn) {
            if (pp.getAtendimentos() == null) continue;
            for (Atendimento a : pp.getAtendimentos()) {
                pedidosPorAtendimento
                        .computeIfAbsent(a.getId(), k -> new ArrayList<>())
                        .add(pp);
            }
        }

        // 2) Ordenar filas e calcular posição/ETA por atendimento
        // Tempo médio por atendimento (segundos). Troque por sua lógica:
        final long TEMPO_MEDIO_DEFAULT = 5 * 60;
        Instant now = Instant.now();

        // Mapa acumulador por paciente: (pedidoId, pacienteId) -> Lista de Items
        // Se você tiver vários pedidos por paciente, pode trocar a chave para apenas pacienteId
        record PacienteKey(String pedidoId, String pacienteId) {}
        Map<PacienteKey, List<QueueBatchUpdateEventDTO.Item>> itensPorPaciente = new HashMap<>();

        for (Map.Entry<UUID, List<PedidoPaciente>> entry : pedidosPorAtendimento.entrySet()) {
            UUID atendimentoId = entry.getKey();
            List<PedidoPaciente> fila = entry.getValue();

            // Ordenação por arrivalAt do Atendimento dentro daquele PedidoPaciente
            fila.sort(Comparator.comparing(pp -> {
                return pp.getAtendimentos().stream()
                        .filter(a -> a.getId().equals(atendimentoId))
                        .map(Atendimento::getArrivalAt)
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElse(now); // se não tiver, considera atual (vai pro fim)
            }));

            // Calcula posição/ETA e agrega por paciente
            for (int i = 0; i < fila.size(); i++) {
                PedidoPaciente pp = fila.get(i);
                int posicao = i + 1;
                long eta = (long) i * TEMPO_MEDIO_DEFAULT;

                QueueBatchUpdateEventDTO.Item item =
                        new QueueBatchUpdateEventDTO.Item(atendimentoId, posicao, eta);

                PacienteKey key = new PacienteKey(pp.getId().toString(), pp.getPacienteId());
                itensPorPaciente.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
            }
        }

        // 3) Para cada paciente, montar 1 evento em lote e disparar
        for (Map.Entry<PacienteKey, List<QueueBatchUpdateEventDTO.Item>> e : itensPorPaciente.entrySet()) {
            PacienteKey key = e.getKey();
            List<QueueBatchUpdateEventDTO.Item> items = e.getValue();
            if (items == null || items.isEmpty()) continue;

            QueueBatchUpdateEventDTO batch = new QueueBatchUpdateEventDTO(
                    "QUEUE.BATCH_POSITION_UPDATED",
                    key.pedidoId(),
                    key.pacienteId(),
                    items,
                    now
            );

            webhookDispatcherService.dispatchBatchUpdate(key.pacienteId(), batch);
        }
    }
}

