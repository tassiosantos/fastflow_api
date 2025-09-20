package com.flashflow.api.service;

import com.flashflow.api.entity.Paciente;
import com.flashflow.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> buscarPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public Paciente adicionarNaFila(Paciente paciente) {
        paciente.setStatus(Paciente.StatusPaciente.AGUARDANDO);
        paciente.setDataChegada(LocalDateTime.now());
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> obterFilaOrdenada() {
        return pacienteRepository.findFilaOrdenada();
    }

    public Optional<Paciente> chamarProximo() {
        List<Paciente> fila = pacienteRepository.findFilaOrdenada();
        if (!fila.isEmpty()) {
            Paciente proximo = fila.get(0);
            proximo.setStatus(Paciente.StatusPaciente.EM_ATENDIMENTO);
            proximo.setDataAtendimento(LocalDateTime.now());
            return Optional.of(pacienteRepository.save(proximo));
        }
        return Optional.empty();
    }

    public Paciente finalizarAtendimento(Long pacienteId) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(pacienteId);
        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            paciente.setStatus(Paciente.StatusPaciente.ATENDIDO);
            return pacienteRepository.save(paciente);
        }
        throw new RuntimeException("Paciente não encontrado");
    }

    public long contarPacientesNaFila() {
        return pacienteRepository.countByStatus(Paciente.StatusPaciente.AGUARDANDO);
    }

    public List<Paciente> listarPorStatus(Paciente.StatusPaciente status) {
        return pacienteRepository.findByStatus(status);
    }

    public Paciente atualizar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}