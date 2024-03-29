package br.com.solocraft.testesUnitariosService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.solocraft.model.Usuario;
import br.com.solocraft.model.dto.UsuarioRequestDTO;
import br.com.solocraft.model.dto.UsuarioResponseDTO;
import br.com.solocraft.repository.UsuarioRepository;
import br.com.solocraft.service.UsuarioService;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository userRepository;

    @InjectMocks
    private UsuarioService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void CadastrarUsuarioStatusValidate() {

        String nome = "mairyeduardo";
        String email = "mairyeduardo2@gmail.com";
        String senha = "200102eduardo";

        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setNome(nome);
        usuarioRequestDTO.setEmail(email);
        usuarioRequestDTO.setSenha(senha);

        UsuarioResponseDTO usuarioCadastrado = userService.cadastrarUsuario(usuarioRequestDTO);
        assertEquals(nome, usuarioCadastrado.getNome());
        assertEquals(email, usuarioCadastrado.getEmail());
        verify(userRepository, times(1)).save(any(Usuario.class));
    }
}

