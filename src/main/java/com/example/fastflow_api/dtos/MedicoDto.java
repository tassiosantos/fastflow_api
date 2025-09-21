package com.example.fastflow_api.dtos;


import com.example.fastflow_api.models.enums.Especialidade;

public class MedicoDto {


    public String nome;

    public Especialidade especialidade;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
