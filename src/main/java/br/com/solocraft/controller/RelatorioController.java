package br.com.solocraft.controller;


import br.com.solocraft.model.RelatorioIndividual;
import br.com.solocraft.model.dto.RelatorioIndividualResponseDTO;
import br.com.solocraft.model.dto.TaskResponseDTO;
import br.com.solocraft.service.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/relatorio")
public class RelatorioController {

    private RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }


    @PostMapping("/{id}")
    public ResponseEntity<RelatorioIndividualResponseDTO> gerarRelatorio(@PathVariable("id") Long id){
        return ResponseEntity.ok(relatorioService.criarRelatorioIndividual(id));
    }

    @GetMapping
    public ResponseEntity<List<RelatorioIndividualResponseDTO>> buscarRelatorio() {
        return ResponseEntity.ok(relatorioService.buscarRelatorios());
    }

    @GetMapping("/tarefa/{id}")
    public ResponseEntity<RelatorioIndividualResponseDTO> buscarRelatorioPorIdTarefa(@PathVariable("id") Long id) {
        return ResponseEntity.ok(relatorioService.buscarRelatorioPorIdDaTarefaDTO(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RelatorioIndividualResponseDTO> removerRelatorioPorId(@PathVariable("id") Long id) {
        RelatorioIndividualResponseDTO relatorioRemovido = relatorioService.removerRelatorioPorId(id);
        return ResponseEntity.ok(relatorioRemovido);
    }

}
