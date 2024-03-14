package br.com.solocraft.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "relatorio_individual_tb")
@Data
public class RelatorioIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_task", referencedColumnName = "id")
    private Task task;
    @Column
    private BigDecimal ValorLiquido;

}
