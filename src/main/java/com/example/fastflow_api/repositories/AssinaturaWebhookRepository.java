package com.example.fastflow_api.repositories;

import com.example.fastflow_api.models.entities.AssinaturaWebhook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AssinaturaWebhookRepository extends JpaRepository<AssinaturaWebhook, UUID> {
    List<AssinaturaWebhook> findAllByPacienteIdAndActiveTrue(String pacienteId);
}


