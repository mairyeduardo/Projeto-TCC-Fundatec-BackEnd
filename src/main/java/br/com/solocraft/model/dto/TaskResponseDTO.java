package br.com.solocraft.model.dto;

import br.com.solocraft.model.Cliente;
import br.com.solocraft.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TaskResponseDTO {

    private Long id;
    private Long idUsuario;
    private String titulo;
    private String descricao;
    private BigDecimal valorServico;
    private BigDecimal custoAtual;
    private BigDecimal custoSoma = null;
    private LocalDate dataInicio;
    private LocalDate dataFinal = null;
    private String enderecoServico;
    private String statusTarefa;
    private Long idCliente;
    private String nomeCliente;
}
