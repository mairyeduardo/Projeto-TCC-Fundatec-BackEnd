package br.com.solocraft.controller;


import br.com.solocraft.model.RelatorioIndividual;
import br.com.solocraft.service.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/relatorio")
public class RelatorioController {

    private RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<RelatorioIndividual> gerarRelatorio(@PathVariable("id") Long id){
        return ResponseEntity.ok(relatorioService.gerarRelatorioIndividual(id));
    }
}
