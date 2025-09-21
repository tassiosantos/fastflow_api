package com.example.fastflow_api.records;

public record LoginResponse(String tokenAcesso, Long criacao, Long expiracao, String mensagem) {
}