package br.com.solocraft.controller;

import br.com.solocraft.model.Usuario;
import br.com.solocraft.model.dto.UsuarioRequestDTO;
import br.com.solocraft.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listartodos")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(this.usuarioService.buscarTodosUsuarios());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Void> adicionarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        usuarioService.cadastrarUsuario(usuarioRequestDTO);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/email-e-senha/{email}/{senha}")
    public ResponseEntity<Usuario> buscarUsuarioPorEmailESenha(@PathVariable("email") String email,
                                                               @PathVariable("senha") String senha) {
        return ResponseEntity.ok(this.usuarioService.buscarUsuarioPorEmailESenha(email, senha));
    }

}
