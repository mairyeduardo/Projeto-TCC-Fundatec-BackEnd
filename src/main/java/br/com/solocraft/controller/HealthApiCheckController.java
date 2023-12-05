package br.com.solocraft.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthApiCheckController {

    @GetMapping
    public ResponseEntity<String> DizerOla(){
        return ResponseEntity.ok("Olá, API solo craft está funcionando corretamente!");

    }

}
