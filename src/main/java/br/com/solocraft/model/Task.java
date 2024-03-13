package br.com.solocraft.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Task_tb")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
    @Column
    private String titulo;
    @Column
    private String descricao;
    @Column
    private BigDecimal valorServico;
    @Column
    private BigDecimal custoInicial;
    @Column
    private LocalDate dataInicio;
    @Column
    private LocalDate dataFinal = null;
    @Column
    private String enderecoServico;
    @Column
    private String statusTarefa;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

}
