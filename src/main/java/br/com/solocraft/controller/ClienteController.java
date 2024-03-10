package br.com.solocraft.controller;

import br.com.solocraft.model.Cliente;
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
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(this.clienteService.buscarCliente());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Cliente> buscarClientePorNome(@PathVariable("nome") String nome){
        return ResponseEntity.ok(this.clienteService.buscarClientePorNome(nome));
    }
}
