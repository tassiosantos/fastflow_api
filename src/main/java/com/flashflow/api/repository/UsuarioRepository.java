package com.flashflow.api.repository;

import com.flashflow.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByLogin(String login);
    
    boolean existsByLogin(String login);
    
    Optional<Usuario> findByLoginAndAtivo(String login, Boolean ativo);
}