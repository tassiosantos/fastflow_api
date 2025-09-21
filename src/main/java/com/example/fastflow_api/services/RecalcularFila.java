package com.example.fastflow_api.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RecalcularFila {

    @Transactional
    public void recalcular() {
        // Lógica para recalcular a fila de atendimentos
        // Isso pode incluir reordenar os atendimentos com base em prioridades, tempos estimados, etc.
    }





    @Transactional
    public void atualizarTemposEstimados() {
        // Lógica para atualizar os tempos estimados de atendimento
        // Isso pode incluir recalcular os tempos com base em novos dados ou mudanças na fila
    }


    @Transactional
    public void notificarMudancas() {
        // Lógica para notificar os pacientes sobre mudanças na fila ou tempos estimados
        // Isso pode incluir enviar e-mails, SMS, ou notificações push
    }







}
