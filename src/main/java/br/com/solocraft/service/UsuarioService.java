package br.com.solocraft.service;

import br.com.solocraft.model.Task;
import br.com.solocraft.model.Usuario;
import br.com.solocraft.model.dto.UsuarioRequestDTO;
import br.com.solocraft.model.dto.UsuarioResponseDTO;
import br.com.solocraft.model.dto.converter.UsuarioConverter;
import br.com.solocraft.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private static final String ERRO_CRIACAO_EMAIL_EXISTENTE = "Não foi possivel cadastrar usuario, email já cadastrado";

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> buscarTodosUsuarios() {

        List<Usuario> buscaDeUsuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> usuarioResponse = new ArrayList<>();

        for (Usuario u: buscaDeUsuarios) {
            usuarioResponse.add(UsuarioConverter.converterEntidadeParaDTO(u));
        }

        if (usuarioResponse.isEmpty()) {
         throw new ResponseStatusException(
           HttpStatus.BAD_REQUEST, "Não existem usuarios cadastrados"
         );
        }

        return usuarioResponse;
    }

    public UsuarioResponseDTO buscarUsuarioPorEmailESenha(String email, String senha) {

        Usuario usuarioBanco = usuarioRepository.findByEmailAndSenha(email, senha);

        if (Objects.isNull(usuarioBanco)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Falha no Login, dados invalidos."
            );
        }

        UsuarioResponseDTO userDTO = UsuarioConverter.converterEntidadeParaDTO(usuarioBanco);
        return userDTO;
    }

    public Usuario buscarUsuarioPorId(Long id) {
        var usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    private Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        String verificarEmailUsuarioASerAdicionado = usuarioRequestDTO.getEmail();
        Usuario usuarioExistente = buscarUsuarioPorEmail(verificarEmailUsuarioASerAdicionado);
        if (Objects.nonNull(usuarioExistente)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ERRO_CRIACAO_EMAIL_EXISTENTE
            );
        }

        Usuario usuarioEntity = UsuarioConverter.converterDTOParaEntidade(usuarioRequestDTO);
        usuarioRepository.save(usuarioEntity);
        return UsuarioConverter.converterEntidadeParaDTO(usuarioEntity);
    }

    public UsuarioResponseDTO removerPorId(Long id) {

        Usuario usuarioASerRemovido = buscarUsuarioPorId(id);

        if (usuarioASerRemovido == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possivel remover, Usuario de id: " + id + " não cadastrado no banco de dados.");
        } else {
            UsuarioResponseDTO usuarioResponseDTO = UsuarioConverter.converterEntidadeParaDTO(usuarioASerRemovido);
            usuarioRepository.delete(usuarioASerRemovido);
            return usuarioResponseDTO;
        }
    }

}
