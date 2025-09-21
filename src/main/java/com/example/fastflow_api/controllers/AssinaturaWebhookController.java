package com.example.fastflow_api.controllers;


import com.example.fastflow_api.models.entities.AssinaturaWebhook;
import com.example.fastflow_api.repositories.AssinaturaWebhookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/webhooks")
public class AssinaturaWebhookController {

    @Autowired
    private AssinaturaWebhookRepository assinaturaWebhookRepository;

    @PostMapping("/subscribe")
    public AssinaturaWebhook assinar(@RequestBody AssinaturaWebhook sub) {
        if (sub.getSenhaAssinatura() == null || sub.getSenhaAssinatura().isBlank()) {
            sub.setSenhaAssinatura(UUID.randomUUID().toString().replace("-", ""));
        }
        sub.setActive(true);
        return assinaturaWebhookRepository.save(sub);
    }

    @PostMapping("/unsubscribe/{id}")
    public void cancelarAssinatura(@PathVariable UUID id) {
        assinaturaWebhookRepository.findById(id).ifPresent(s -> {
            s.setActive(false);
            assinaturaWebhookRepository.save(s);
        });
    }

}
