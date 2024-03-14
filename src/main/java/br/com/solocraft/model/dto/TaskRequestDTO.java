package br.com.solocraft.model.dto;

import br.com.solocraft.model.Cliente;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TaskRequestDTO {

    private Long idUsuario;
    private String titulo;
    private String descricao;
    private BigDecimal valorServico;
    private BigDecimal custoAtual = BigDecimal.valueOf(00.00);
    private BigDecimal custoSoma = null;
    private LocalDate dataInicio;
    private String enderecoServico;
    private Cliente cliente;
}
