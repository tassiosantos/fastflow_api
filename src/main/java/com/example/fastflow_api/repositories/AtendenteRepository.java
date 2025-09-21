package com.example.fastflow_api.repositories;


import com.example.fastflow_api.models.entities.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, UUID> {


    Optional<Atendente> findByLogin(String login);

}
