package br.com.solocraft.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Cliente_tb")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
    @Column
    private String nome;
    @Column
    private String telefone;
    @Column
    private String enderecoPrincipal;

}
