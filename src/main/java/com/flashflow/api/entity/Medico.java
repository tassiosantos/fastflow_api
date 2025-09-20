package com.flashflow.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "medico")
public class Medico extends Usuario {

    @NotBlank(message = "CRM é obrigatório")
    @Size(max = 20, message = "CRM deve ter no máximo 20 caracteres")
    @Column(name = "crm", unique = true, nullable = false)
    private String crm;

    @Size(max = 50, message = "Especialidade deve ter no máximo 50 caracteres")
    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "disponivel")
    private Boolean disponivel = true;

    public Medico() {
        super();
    }

    public Medico(String login, String senha, String nome, String crm, String especialidade) {
        super(login, senha, nome);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    // Getters and Setters
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + getId() +
                ", login='" + getLogin() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}