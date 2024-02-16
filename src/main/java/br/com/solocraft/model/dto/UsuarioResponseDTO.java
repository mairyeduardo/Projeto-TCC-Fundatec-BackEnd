package br.com.solocraft.model.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
}
