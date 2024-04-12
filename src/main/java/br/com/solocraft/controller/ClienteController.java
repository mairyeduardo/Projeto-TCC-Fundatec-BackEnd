package br.com.solocraft.controller;

import br.com.solocraft.model.Cliente;
import br.com.solocraft.model.dto.ClienteResponseDTO;
import br.com.solocraft.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        return ResponseEntity.ok(this.clienteService.buscarCliente());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<ClienteResponseDTO>> buscarClientePorIdUsuario(@PathVariable("id") Long id){
        return ResponseEntity.ok(clienteService.buscarClientePorIdDoUsuario(id));
    }

    @GetMapping("/{id}/{nome}")
    public ResponseEntity<List<ClienteResponseDTO>> buscarClientePorNomeUsandoIDUsuario(@PathVariable("id") Long id, @PathVariable("nome") String nome){
        return ResponseEntity.ok(this.clienteService.buscarClientePorNomeUtilizandoIdDoUsuario(id, nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> removerClientePorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.clienteService.removerPorId(id));
    }
}
