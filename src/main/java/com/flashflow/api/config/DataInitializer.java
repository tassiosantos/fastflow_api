package com.flashflow.api.config;

import com.flashflow.api.entity.Atendente;
import com.flashflow.api.entity.Medico;
import com.flashflow.api.entity.Paciente;
import com.flashflow.api.entity.TecnicoSaude;
import com.flashflow.api.repository.UsuarioRepository;
import com.flashflow.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criar usuários de exemplo
        if (usuarioRepository.count() == 0) {
            // Médicos
            Medico medico1 = new Medico("dr.silva", "123456", "Dr. João Silva", "12345-SP", "Cardiologia");
            Medico medico2 = new Medico("dra.santos", "123456", "Dra. Maria Santos", "67890-SP", "Pediatria");
            
            // Atendentes
            Atendente atendente1 = new Atendente("atendente1", "123456", "Ana Costa", "Recepção");
            atendente1.setHorarioInicio("08:00");
            atendente1.setHorarioFim("17:00");
            
            // Técnico de Saúde
            TecnicoSaude tecnico1 = new TecnicoSaude("tecnico1", "123456", "Carlos Oliveira", "Enfermagem");
            tecnico1.setRegistroProfissional("COREN-12345");

            usuarioRepository.save(medico1);
            usuarioRepository.save(medico2);
            usuarioRepository.save(atendente1);
            usuarioRepository.save(tecnico1);

            System.out.println("✅ Usuários de exemplo criados!");
        }

        // Criar pacientes de exemplo
        if (pacienteRepository.count() == 0) {
            Paciente paciente1 = new Paciente("José da Silva", "12345678901", Paciente.PrioridadeAtendimento.NORMAL);
            paciente1.setTelefone("(11) 99999-1111");
            paciente1.setObservacoes("Consulta de rotina");

            Paciente paciente2 = new Paciente("Maria Fernanda", "98765432100", Paciente.PrioridadeAtendimento.URGENTE);
            paciente2.setTelefone("(11) 99999-2222");
            paciente2.setObservacoes("Dor no peito");

            pacienteRepository.save(paciente1);
            pacienteRepository.save(paciente2);

            System.out.println("✅ Pacientes de exemplo criados!");
        }
    }
}