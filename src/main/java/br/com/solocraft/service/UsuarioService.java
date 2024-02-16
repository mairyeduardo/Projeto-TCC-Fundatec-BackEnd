package br.com.solocraft.service;

import br.com.solocraft.model.Usuario;
import br.com.solocraft.model.dto.UsuarioRequestDTO;
import br.com.solocraft.model.dto.UsuarioResponseDTO;
import br.com.solocraft.model.dto.converter.UsuarioConverter;
import br.com.solocraft.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private static final String ERRO_CRIACAO_EMAIL_EXISTENTE = "Não foi possivel cadastrar usuario, email já cadastrado";

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorEmailESenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    private Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        String verificarEmailUsuarioASerAdicionado = usuarioRequestDTO.getEmail();
        Usuario usuarioExistente = buscarUsuarioPorEmail(verificarEmailUsuarioASerAdicionado);
        if (usuarioExistente != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ERRO_CRIACAO_EMAIL_EXISTENTE
            );
        } else {
            Usuario usuarioEntity = UsuarioConverter.converterDTOParaEntidade(usuarioRequestDTO);
            usuarioRepository.save(usuarioEntity);
        }
    }

    public UsuarioResponseDTO removerPorId(Long id) {

        Usuario usuarioASerRemovido = usuarioRepository.findById(id).get();

        if (usuarioASerRemovido == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possivel remover, Usuario de id: " +id+ " não cadastrado no banco de dados.");
        } else {
            UsuarioResponseDTO usuarioResponseDTO = UsuarioConverter.converterEntidadeParaDTO(usuarioASerRemovido);
            usuarioRepository.delete(usuarioASerRemovido);
            return usuarioResponseDTO;
        }
    }

//    public Usuario alterarSenha(Long id, Usuario usuario) {
//        Usuario usuarioBuscadoNoBanco = usuarioRepository.findById(id).get();
//        usuarioBuscadoNoBanco.setSenha(usuario.getSenha());
//        usuarioRepository.save(usuarioBuscadoNoBanco);
//        return usuarioBuscadoNoBanco;
//    }
}
