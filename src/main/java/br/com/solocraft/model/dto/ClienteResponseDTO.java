package br.com.solocraft.model.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {

    private Long id;
    private Long idUsuario;
    private String nome;
    private String telefone;
    private String enderecoPrincipal;
}
