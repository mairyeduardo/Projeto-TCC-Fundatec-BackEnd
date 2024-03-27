package br.com.solocraft.service;

import br.com.solocraft.model.RelatorioIndividual;
import br.com.solocraft.model.Task;
import br.com.solocraft.model.dto.RelatorioIndividualResponseDTO;
import br.com.solocraft.model.dto.TaskResponseDTO;
import br.com.solocraft.model.dto.converter.RelatorioIndividualConverter;
import br.com.solocraft.model.dto.converter.TaskConverter;
import br.com.solocraft.repository.RelatorioIndividualRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RelatorioService {

    private TaskService taskService;

    private RelatorioIndividualRepository relatorioIndividualRepository;

    public RelatorioService(TaskService taskService, RelatorioIndividualRepository relatorioIndividualRepository) {
        this.taskService = taskService;
        this.relatorioIndividualRepository = relatorioIndividualRepository;
    }

    public RelatorioIndividualResponseDTO criarRelatorioIndividual(Long id){

        Task taskEncontrada = taskService.buscarTaskPorId(id);
        String statusAtual = taskEncontrada.getStatusTarefa();

        if (Objects.isNull(taskEncontrada)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possivel gerar relatório, Tarefa de id: " + id + " não cadastrado no banco de dados.");
        }

        if (!statusAtual.equals("Finalizado")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possivel Gerar Relatório de uma tarefa que não está em Finalizada"
            );
        }

        RelatorioIndividual relatorioIndividual = new RelatorioIndividual();
        relatorioIndividual.setTask(taskEncontrada);
        LocalDate datainicio = taskEncontrada.getDataInicio();
        LocalDate dataFim = taskEncontrada.getDataFinal();
        relatorioIndividual.setTotalDeDias(ChronoUnit.DAYS.between(datainicio, dataFim));
        BigDecimal valorCobrado = taskEncontrada.getValorServico();
        BigDecimal valorGasto = taskEncontrada.getCustoAtual();
        BigDecimal valorLiquido = valorCobrado.subtract(valorGasto);
        relatorioIndividual.setValorLiquido(valorLiquido);

        relatorioIndividualRepository.save(relatorioIndividual);

        return RelatorioIndividualConverter.converterEntidadeParaDTO(relatorioIndividual);
    }

    public List<RelatorioIndividualResponseDTO> buscarRelatorios() {
            List<RelatorioIndividual> relatorioIndividuais = relatorioIndividualRepository.findAll();
            List<RelatorioIndividualResponseDTO> relatorioIndividualResponseDTO = new ArrayList<>();

            for (RelatorioIndividual r : relatorioIndividuais) {
                relatorioIndividualResponseDTO.add(RelatorioIndividualConverter.converterEntidadeParaDTO(r));
            }
            return relatorioIndividualResponseDTO;
    }
}