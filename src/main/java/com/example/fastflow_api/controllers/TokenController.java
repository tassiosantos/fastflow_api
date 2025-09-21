package com.example.fastflow_api.controllers;

import com.example.fastflow_api.records.LoginRequest;
import com.example.fastflow_api.records.LoginResponse;
import com.example.fastflow_api.services.APIServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static java.time.LocalTime.now;

@RestController
public class TokenController{

    private final JwtEncoder jwtEncoder;

    private BCryptPasswordEncoder passwordEncoder;

    private final APIServiceImpl apiService;


    public TokenController(JwtEncoder jwtEncoder, BCryptPasswordEncoder passwordEncoder, APIServiceImpl apiService) {
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
        this.apiService = apiService;
    }


    @PostMapping("/atendente/login")
    public ResponseEntity<LoginResponse> loginAtendente(@RequestBody LoginRequest loginRequest){
        Instant agora = Instant.now();
        var expiresIn = 300L;

        var atendente = apiService.buscarAtendenteLogin(loginRequest.login());

        if (atendente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new LoginResponse("---", agora.toEpochMilli(), 0L, "Usuário não cadastrado"));
        } else{
            if(apiService.isSenhaValida(passwordEncoder, loginRequest.senha(), atendente.get().getSenha())){
                var claims = JwtClaimsSet.builder()
                        .issuer("backend-fastflow")
                        .claim("login", atendente.get().getLogin())
                        .claim("nome", atendente.get().getNome())
                        .issuedAt(agora)
                        .expiresAt(agora.plusSeconds(expiresIn))
                        .build();

                var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

                return ResponseEntity.ok(new LoginResponse(jwtValue, agora.toEpochMilli(), agora.plusSeconds(expiresIn).toEpochMilli(), "Login realizado com sucesso."));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new LoginResponse("---", agora.toEpochMilli(), 0L, "Senha inválida"));
            }

        }
    }


    @PostMapping("/medico/login")
    public ResponseEntity<LoginResponse> loginMedico(@RequestBody LoginRequest loginRequest){
        Instant agora = Instant.now();
        var expiresIn = 300L;

        var medico = apiService.buscarMedicoLogin(loginRequest.login());

        if (medico.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new LoginResponse("---", agora.toEpochMilli(), 0L, "Usuário não cadastrado"));
        } else{
            if(apiService.isSenhaValida(passwordEncoder, loginRequest.senha(), medico.get().getSenha())){
                var claims = JwtClaimsSet.builder()
                        .issuer("backend-fastflow")
                        .claim("login", medico.get().getLogin())
                        .claim("nome", medico.get().getNome())
                        .issuedAt(agora)
                        .expiresAt(agora.plusSeconds(expiresIn))
                        .build();

                var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

                return ResponseEntity.ok(new LoginResponse(jwtValue, agora.toEpochMilli(), agora.plusSeconds(expiresIn).toEpochMilli(), "Login realizado com sucesso."));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new LoginResponse("---", agora.toEpochMilli(), 0L, "Senha inválida"));
            }

        }
    }





}