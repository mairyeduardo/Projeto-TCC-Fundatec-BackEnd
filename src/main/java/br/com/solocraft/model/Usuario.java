package br.com.solocraft.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Usuario_Tb")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String senha;
}
