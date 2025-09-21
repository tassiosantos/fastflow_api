package com.example.fastflow_api.models.entities;

import com.example.fastflow_api.models.enums.TipoServico;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    private String nome;

     private String sala;

    private Instant tempoMedio;

    @Enumerated(EnumType.STRING)
    private TipoServico tipo;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Instant getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(Instant tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public TipoServico getTipo() {
        return tipo;
    }

    public void setTipo(Enum tipo) {
        this.tipo = (TipoServico) tipo;
    }
}
