package com.flashflow.api.repository;

import com.flashflow.api.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
    List<Medico> findByDisponivel(Boolean disponivel);
    
    List<Medico> findByEspecialidade(String especialidade);
    
    Optional<Medico> findByCrm(String crm);
    
    boolean existsByCrm(String crm);
}