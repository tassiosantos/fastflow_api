package com.example.fastflow_api.services;


import com.example.fastflow_api.models.entities.Atendente;
import com.example.fastflow_api.models.entities.Medico;
import com.example.fastflow_api.repositories.AtendenteRepository;
import com.example.fastflow_api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class APIServiceImpl{

    @Autowired
    AtendenteRepository atendenteRepository;

    @Autowired
    MedicoRepository medicoRepository;



    public Optional<Atendente> buscarAtendenteLogin(String login) {
        return atendenteRepository.findByLogin(login);
    }

    public Optional<Medico> buscarMedicoLogin(String login) {
        return medicoRepository.findByLogin(login);
    }

    public boolean isSenhaValida(PasswordEncoder passwordEncoder, String senhaRequest, String senhaUsuario) {
        return passwordEncoder.matches(senhaRequest, senhaUsuario);
    }


    public void salvarAtendente(Atendente user) {
        atendenteRepository.save(user);
    }

    public void salvarMedico(Medico user) {
        medicoRepository.save(user);
    }

}
