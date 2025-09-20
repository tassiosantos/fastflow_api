package com.flashflow.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "atendente")
public class Atendente extends Usuario {

    @Size(max = 20, message = "Setor deve ter no máximo 20 caracteres")
    @Column(name = "setor")
    private String setor;

    @Column(name = "horario_inicio")
    private String horarioInicio;

    @Column(name = "horario_fim")
    private String horarioFim;

    public Atendente() {
        super();
    }

    public Atendente(String login, String senha, String nome, String setor) {
        super(login, senha, nome);
        this.setor = setor;
    }

    // Getters and Setters
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    @Override
    public String toString() {
        return "Atendente{" +
                "id=" + getId() +
                ", login='" + getLogin() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", setor='" + setor + '\'' +
                ", horarioInicio='" + horarioInicio + '\'' +
                ", horarioFim='" + horarioFim + '\'' +
                '}';
    }
}