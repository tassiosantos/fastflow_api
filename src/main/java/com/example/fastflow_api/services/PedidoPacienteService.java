package com.example.fastflow_api.services;

import com.example.fastflow_api.models.entities.PedidoPaciente;
import com.example.fastflow_api.repositories.PedidoPacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoPacienteService {

    @Autowired
    PedidoPacienteRepository pedidoPacienteRepository;

    @Transactional
    public void checkInPedido(String pedidoId) {

        Optional<PedidoPaciente> pedidoOpt = pedidoPacienteRepository.findByPacienteId(pedidoId);

        if(pedidoOpt.isEmpty() || pedidoOpt.get() == null) {
            throw new RuntimeException("Pedido não encontrado para o paciente ID: " + pedidoId);
        }

        PedidoPaciente pedidoPaciente = pedidoOpt.get();
        if(pedidoPaciente.isCheckedIn()) {
            throw new RuntimeException("Pedido já está com check-in realizado para o paciente ID: " + pedidoId);
        }else {
            pedidoPaciente.setCheckedIn(true);
            pedidoPacienteRepository.save(pedidoPaciente);
        }
    }






}
