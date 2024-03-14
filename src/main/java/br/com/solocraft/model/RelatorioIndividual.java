package br.com.solocraft.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "relatorio_individual_tb")
@Data
public class RelatorioIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
