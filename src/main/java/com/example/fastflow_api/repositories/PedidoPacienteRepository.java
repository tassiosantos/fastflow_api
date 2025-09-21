package com.example.fastflow_api.repositories;

import com.example.fastflow_api.models.entities.PedidoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoPacienteRepository extends JpaRepository<PedidoPaciente, UUID> {


    Optional<PedidoPaciente> findByPacienteId(String pedidoId);

    List<PedidoPaciente> findAllByCheckedInTrue();
}
