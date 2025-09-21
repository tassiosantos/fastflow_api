package com.example.fastflow_api.models.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class AssinaturaWebhook {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String assinanteUrl;


    @Column(nullable = false)
    private String pacienteId;

    @Column(nullable = false)
    private String senhaAssinatura;

    @Column(nullable = false)
    private String evento;

    private boolean active = true;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAssinanteUrl() {
        return assinanteUrl;
    }

    public void setAssinanteUrl(String assinanteUrl) {
        this.assinanteUrl = assinanteUrl;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getSenhaAssinatura() {
        return senhaAssinatura;
    }

    public void setSenhaAssinatura(String senhaAssinatura) {
        this.senhaAssinatura = senhaAssinatura;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}