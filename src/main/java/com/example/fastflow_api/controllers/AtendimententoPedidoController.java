package com.example.fastflow_api.controllers;

import com.example.fastflow_api.repositories.PedidoPacienteRepository;
import com.example.fastflow_api.services.AtendimentoPedidoService;
import com.example.fastflow_api.services.PedidoPacienteService;
import com.example.fastflow_api.services.WebhookDispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtendimententoPedidoController {

    @Autowired
    AtendimentoPedidoService atendimentoPedidoService;


    @PutMapping("/checkin/{pedidoId}" )
    public void checkinPedido(@PathVariable String pedidoId) {
        atendimentoPedidoService.checkInPedido(pedidoId);
    }


    @GetMapping("/recalcularfila")
    public ResponseEntity<String> recalcularFila() {

        atendimentoPedidoService.recalcularFilaAtendimento();

        return ResponseEntity.ok("Fila de atendimento recalculada com sucesso.");
    }






}
