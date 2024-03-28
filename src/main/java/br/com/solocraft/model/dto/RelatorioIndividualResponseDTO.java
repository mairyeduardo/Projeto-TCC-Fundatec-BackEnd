package br.com.solocraft.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RelatorioIndividualResponseDTO {

    private Long id;
    private Long idTarefa;
    private String tituloTarefa;
    private String descricaoTarefa;
    private BigDecimal valorServico;
    private BigDecimal custoAtual;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private Long totalDeDias;
    private String enderecoServico;
    private String nomeCliente;
    private BigDecimal ValorLiquido;

}
