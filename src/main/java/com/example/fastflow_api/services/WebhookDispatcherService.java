package com.example.fastflow_api.services;

import com.example.fastflow_api.dtos.QueueBatchUpdateEventDTO;
import com.example.fastflow_api.models.entities.AssinaturaWebhook;
import com.example.fastflow_api.records.QueueUpdateEventDTO;
import com.example.fastflow_api.repositories.AssinaturaWebhookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
public class WebhookDispatcherService {

    private final AssinaturaWebhookRepository assinaturaWebhookRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public WebhookDispatcherService(AssinaturaWebhookRepository assinaturaWebhookRepository) {
        this.assinaturaWebhookRepository = assinaturaWebhookRepository;
        this.restTemplate = new RestTemplate();
    }

    public void dispatchBatchUpdate(String pacienteId, QueueBatchUpdateEventDTO batch) {
        List<AssinaturaWebhook> subs = assinaturaWebhookRepository.findAllByPacienteIdAndActiveTrue(pacienteId);
        if (subs.isEmpty() || batch.items()==null) return;

        for (AssinaturaWebhook sub : subs) {
            if (!sub.getEvento().equals(batch.eventType())) continue;
            sendOne(sub, batch);
        }
    }


    private void sendOne(AssinaturaWebhook sub, QueueBatchUpdateEventDTO batch) {
        try {
            String body = new ObjectMapper().writeValueAsString(batch);
            String signature = hmacSha256Hex(sub.getSenhaAssinatura(), body);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Webhook-Event", batch.eventType());
            headers.add("X-Webhook-Delivery-Id", UUID.randomUUID().toString());
            headers.add("X-Webhook-Signature", "sha256=" + signature);

            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> resp = restTemplate.postForEntity(sub.getAssinanteUrl(), entity, String.class);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar webhook para " + sub.getAssinanteUrl(), e);
        }
    }

    private String hmacSha256Hex(String secret, String body) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] raw = mac.doFinal(body.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : raw) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao assinar webhook", e);
        }
    }
}

