package com.example.fastflow_api.dtos;

import java.util.UUID;

public class AtendenteDto {

    public UUID id;

    public String nome;

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
}
