package br.com.solocraft.model.dto.converter;

import br.com.solocraft.model.Usuario;
import br.com.solocraft.model.dto.UsuarioRequestDTO;

public class UsuarioConverter {

    public static Usuario converterParaEntidade(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNome(usuarioRequestDTO.getNome());
        usuarioEntity.setEmail(usuarioRequestDTO.getEmail());
        usuarioEntity.setSenha(usuarioRequestDTO.getSenha());

    return usuarioEntity;
    }
}
