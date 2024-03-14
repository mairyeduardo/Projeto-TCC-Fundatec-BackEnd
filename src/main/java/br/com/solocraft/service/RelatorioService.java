package br.com.solocraft.service;

import br.com.solocraft.model.RelatorioIndividual;
import br.com.solocraft.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class RelatorioService {

    private TaskService taskService;

    public RelatorioService(TaskService taskService) {
        this.taskService = taskService;
    }

    public RelatorioIndividual gerarRelatorioIndividual(Long id){

        Task taskEncontrada = taskService.buscarTaskPorId(id);

        if (taskEncontrada == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possivel gerar relatório, Tarefa de id: " + id + " não cadastrado no banco de dados.");
        }

        RelatorioIndividual relatorioIndividual = new RelatorioIndividual();
        relatorioIndividual.setTask(taskEncontrada);
        BigDecimal valorCobrado = taskEncontrada.getValorServico();
        BigDecimal valorGasto = taskEncontrada.getCustoAtual();
        BigDecimal valorLiquido = valorCobrado.subtract(valorGasto);
        relatorioIndividual.setValorLiquido(valorLiquido);

        return relatorioIndividual;
    }
}
