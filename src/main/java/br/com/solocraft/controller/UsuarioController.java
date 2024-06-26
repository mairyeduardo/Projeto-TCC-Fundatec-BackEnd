package br.com.solocraft.controller;

import br.com.solocraft.model.Usuario;
import br.com.solocraft.model.dto.UsuarioRequestDTO;
import br.com.solocraft.model.dto.UsuarioResponseDTO;
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
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(this.usuarioService.buscarTodosUsuarios());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<UsuarioResponseDTO> adicionarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.ok(this.usuarioService.cadastrarUsuario(usuarioRequestDTO));
    }

    @GetMapping("/email-e-senha/{email}/{senha}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmailESenha(@PathVariable("email") String email,
                                                               @PathVariable("senha") String senha) {
        return ResponseEntity.ok(this.usuarioService.buscarUsuarioPorEmailESenha(email, senha));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<UsuarioResponseDTO> removerUsuarioPorId(@PathVariable("id") Long id) {
        UsuarioResponseDTO usuarioRemovido = usuarioService.removerPorId(id);
        return ResponseEntity.ok(usuarioRemovido);
    }

}
