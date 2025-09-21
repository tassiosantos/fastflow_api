package com.example.fastflow_api.records;

import java.time.Instant;
import java.util.UUID;

public record QueueUpdateEventDTO(
        String eventType,            // "QUEUE.POSITION_UPDATED"
        String pedidoId,
        String pacienteId,
        UUID atendimentoId,
        int posicao,                 // 1 = primeiro na fila
        Long etaSeconds,             // estimativa em segundos até ser atendido
        Instant updatedAt
) {}