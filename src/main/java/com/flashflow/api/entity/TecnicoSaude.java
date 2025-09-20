package com.flashflow.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tecnico_saude")
public class TecnicoSaude extends Usuario {

    @Size(max = 50, message = "Área de atuação deve ter no máximo 50 caracteres")
    @Column(name = "area_atuacao")
    private String areaAtuacao;

    @Size(max = 20, message = "Registro profissional deve ter no máximo 20 caracteres")
    @Column(name = "registro_profissional")
    private String registroProfissional;

    @Column(name = "certificado_ativo")
    private Boolean certificadoAtivo = true;

    public TecnicoSaude() {
        super();
    }

    public TecnicoSaude(String login, String senha, String nome, String areaAtuacao) {
        super(login, senha, nome);
        this.areaAtuacao = areaAtuacao;
    }

    // Getters and Setters
    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public Boolean getCertificadoAtivo() {
        return certificadoAtivo;
    }

    public void setCertificadoAtivo(Boolean certificadoAtivo) {
        this.certificadoAtivo = certificadoAtivo;
    }

    @Override
    public String toString() {
        return "TecnicoSaude{" +
                "id=" + getId() +
                ", login='" + getLogin() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", areaAtuacao='" + areaAtuacao + '\'' +
                ", registroProfissional='" + registroProfissional + '\'' +
                ", certificadoAtivo=" + certificadoAtivo +
                '}';
    }
}