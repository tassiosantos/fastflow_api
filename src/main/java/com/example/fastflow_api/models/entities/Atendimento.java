package com.example.fastflow_api.models.entities;

import com.example.fastflow_api.models.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.UUID;


@Entity
public class Atendimento {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    private Instant arrivalAt;

    private Instant tempoFila;

    private Float peso;

    private Status status;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getTempoFila() {
        return tempoFila;
    }

    public void setTempoFila(Instant tempoFila) {
        this.tempoFila = tempoFila;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getArrivalAt() {
        return arrivalAt;
    }

    public void setArrivalAt(Instant arrivalAt) {
        this.arrivalAt = arrivalAt;
    }
}
