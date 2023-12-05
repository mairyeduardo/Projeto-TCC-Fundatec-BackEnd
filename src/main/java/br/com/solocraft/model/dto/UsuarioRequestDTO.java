package br.com.solocraft.model.dto;

import lombok.Data;

@Data
public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String senha;
}
