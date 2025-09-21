package com.example.fastflow_api.models.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class PedidoPaciente {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    private boolean checkedIn;

    private String pacienteId;

    @OneToMany
    @JoinTable(
            name = "pedido_paciente_atendimentos",
            joinColumns = @JoinColumn(name = "pedido_paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "atendimento_id")
    )
    private List<Atendimento> atendimentos;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }


    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
