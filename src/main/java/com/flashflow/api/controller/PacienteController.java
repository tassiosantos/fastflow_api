package com.flashflow.api.controller;

import com.flashflow.api.entity.Paciente;
import com.flashflow.api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        return paciente.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Paciente> buscarPorCpf(@PathVariable String cpf) {
        Optional<Paciente> paciente = pacienteService.buscarPorCpf(cpf);
        return paciente.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> adicionarNaFila(@Valid @RequestBody Paciente paciente) {
        try {
            Paciente novoPaciente = pacienteService.adicionarNaFila(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao adicionar paciente na fila: " + e.getMessage());
        }
    }

    @GetMapping("/fila")
    public ResponseEntity<List<Paciente>> obterFilaOrdenada() {
        List<Paciente> fila = pacienteService.obterFilaOrdenada();
        return ResponseEntity.ok(fila);
    }

    @PostMapping("/chamar-proximo")
    public ResponseEntity<?> chamarProximo() {
        try {
            Optional<Paciente> proximo = pacienteService.chamarProximo();
            if (proximo.isPresent()) {
                return ResponseEntity.ok(proximo.get());
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao chamar próximo paciente: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/finalizar-atendimento")
    public ResponseEntity<?> finalizarAtendimento(@PathVariable Long id) {
        try {
            Paciente paciente = pacienteService.finalizarAtendimento(id);
            return ResponseEntity.ok(paciente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao finalizar atendimento: " + e.getMessage());
        }
    }

    @GetMapping("/fila/contagem")
    public ResponseEntity<Long> contarPacientesNaFila() {
        long quantidade = pacienteService.contarPacientesNaFila();
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Paciente>> listarPorStatus(@PathVariable Paciente.StatusPaciente status) {
        List<Paciente> pacientes = pacienteService.listarPorStatus(status);
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
        try {
            Optional<Paciente> pacienteExistente = pacienteService.buscarPorId(id);
            if (pacienteExistente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            paciente.setId(id);
            Paciente pacienteAtualizado = pacienteService.atualizar(paciente);
            return ResponseEntity.ok(pacienteAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            Optional<Paciente> paciente = pacienteService.buscarPorId(id);
            if (paciente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            pacienteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar paciente: " + e.getMessage());
        }
    }
}