package com.example.fastflow_api.models.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Atendente {

        @Id
        @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
        private UUID id;

        private String login;

        private String senha;

        private String nome;

        @Enumerated(EnumType.STRING)
        private Role role;

        public Role getRole() {
            return role;
        }

    public void setRole(Role role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
