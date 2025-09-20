package com.flashflow.api.controller;

import com.flashflow.api.entity.Medico;
import com.flashflow.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        List<Medico> medicos = medicoRepository.findAll();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        return medico.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<Medico> buscarPorCrm(@PathVariable String crm) {
        Optional<Medico> medico = medicoRepository.findByCrm(crm);
        return medico.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Medico>> listarDisponiveis() {
        List<Medico> medicos = medicoRepository.findByDisponivel(true);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<Medico>> listarPorEspecialidade(@PathVariable String especialidade) {
        List<Medico> medicos = medicoRepository.findByEspecialidade(especialidade);
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Medico medico) {
        try {
            if (medicoRepository.existsByCrm(medico.getCrm())) {
                return ResponseEntity.badRequest()
                    .body("CRM já cadastrado: " + medico.getCrm());
            }
            Medico novoMedico = medicoRepository.save(medico);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoMedico);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar médico: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Medico medico) {
        try {
            Optional<Medico> medicoExistente = medicoRepository.findById(id);
            if (medicoExistente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            medico.setId(id);
            Medico medicoAtualizado = medicoRepository.save(medico);
            return ResponseEntity.ok(medicoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar médico: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/disponibilidade")
    public ResponseEntity<?> alterarDisponibilidade(@PathVariable Long id, @RequestParam boolean disponivel) {
        try {
            Optional<Medico> medicoOpt = medicoRepository.findById(id);
            if (medicoOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Medico medico = medicoOpt.get();
            medico.setDisponivel(disponivel);
            Medico medicoAtualizado = medicoRepository.save(medico);
            return ResponseEntity.ok(medicoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao alterar disponibilidade: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            Optional<Medico> medico = medicoRepository.findById(id);
            if (medico.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            medicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar médico: " + e.getMessage());
        }
    }
}