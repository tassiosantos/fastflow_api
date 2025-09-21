package com.example.fastflow_api.dtos;


import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record QueueBatchUpdateEventDTO(
        String eventType,
        String pedidoId,
        String pacienteId,
        List<Item> items,
        Instant updatedAt
) {
    public record Item(
            UUID atendimentoId,
            int posicao,
            long etaSeconds
    ) {}
}