package com.flashflow.api.repository;

import com.flashflow.api.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    List<Paciente> findByStatus(Paciente.StatusPaciente status);
    
    List<Paciente> findByPrioridade(Paciente.PrioridadeAtendimento prioridade);
    
    Optional<Paciente> findByCpf(String cpf);
    
    @Query("SELECT p FROM Paciente p WHERE p.status = 'AGUARDANDO' ORDER BY " +
           "CASE p.prioridade " +
           "WHEN 'EMERGENCIA' THEN 1 " +
           "WHEN 'URGENTE' THEN 2 " +
           "WHEN 'NORMAL' THEN 3 " +
           "END, p.dataChegada ASC")
    List<Paciente> findFilaOrdenada();
    
    long countByStatus(Paciente.StatusPaciente status);
}