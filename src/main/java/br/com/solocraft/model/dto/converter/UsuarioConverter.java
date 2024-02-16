package br.com.solocraft.model.dto.converter;

import br.com.solocraft.model.Usuario;
import br.com.solocraft.model.dto.UsuarioRequestDTO;
import br.com.solocraft.model.dto.UsuarioResponseDTO;

public class UsuarioConverter {

    public static Usuario converterDTOParaEntidade(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setNome(usuarioRequestDTO.getNome());
        usuarioEntity.setEmail(usuarioRequestDTO.getEmail());
        usuarioEntity.setSenha(usuarioRequestDTO.getSenha());

    return usuarioEntity;
    }

    public static UsuarioResponseDTO converterEntidadeParaDTO(Usuario usuario) {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setId(usuario.getId());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setEmail(usuario.getEmail());
        return usuarioResponseDTO;
    }

}
