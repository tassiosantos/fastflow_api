package com.example.fastflow_api.config;

import com.example.fastflow_api.models.entities.Atendente;
import com.example.fastflow_api.models.entities.Atendimento;
import com.example.fastflow_api.models.entities.Medico;
import com.example.fastflow_api.models.entities.Role;
import com.example.fastflow_api.models.enums.Especialidade;
import com.example.fastflow_api.services.APIServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class AdminConfig implements CommandLineRunner {


    private final APIServiceImpl apiService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminConfig(
            APIServiceImpl apiService, APIServiceImpl apiService1,
            BCryptPasswordEncoder passwordEncoder) {
        this.apiService = apiService1;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {


        var userAdmin = apiService.buscarAtendenteLogin("atendente");
        userAdmin.ifPresentOrElse(
                user -> System.out.println("admin já existe"),
                () -> {
                    var user = new Atendente();
                    user.setNome("Veronica");
                    user.setLogin("atendente");
                    user.setSenha(passwordEncoder.encode("123456"));
                    user.setRole(Role.ATENDENTE);
                    apiService.salvarAtendente(user);
                    System.out.println("Usuário admin criado");
                }
        );

        var userMedico = apiService.buscarMedicoLogin("medico");
        userMedico.ifPresentOrElse(
                user -> System.out.println("medico já existe"),
                () -> {
                    var user = new Medico();
                    user.setNome("medico");
                    user.setLogin("medico");
                    user.setSenha(passwordEncoder.encode("123456"));
                    user.setEspecialidade(Especialidade.CARDIOLOGIA);
                    user.setRole(Role.MEDICO);
                    apiService.salvarMedico(user);
                    System.out.println("Usuário medico criado");
                }
        );


    }


}