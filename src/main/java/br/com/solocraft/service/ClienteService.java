package br.com.solocraft.service;

import br.com.solocraft.model.Cliente;
import br.com.solocraft.model.Usuario;
import br.com.solocraft.model.dto.ClienteRequestDTO;
import br.com.solocraft.model.dto.UsuarioRequestDTO;
import br.com.solocraft.model.dto.converter.ClienteConverter;
import br.com.solocraft.model.dto.converter.UsuarioConverter;
import br.com.solocraft.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    public  UsuarioService usuarioService;

    public ClienteService(ClienteRepository clienteRepository, UsuarioService usuarioService){
        this.clienteRepository = clienteRepository;
        this.usuarioService = usuarioService;
    }

    public Cliente buscarClientePorNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    public List<Cliente> buscarCliente() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes;
    }

    public Cliente buscarClientePorTelefone(String telefone) {
        return clienteRepository.findByTelefone(telefone);
    }

    public void cadastrarCliente(Cliente cliente){
            String nomeNovoCliente = cliente.getNome();
            Cliente nomeClienteExistente = buscarClientePorNome(nomeNovoCliente);

            String telefoneNovoCliente = cliente.getTelefone();
            Cliente telefoneClienteExistente = buscarClientePorTelefone(telefoneNovoCliente);

            var usuario  = usuarioService.buscarUsuarioPorId(cliente.getUsuario().getId());

            if (Objects.isNull(usuario)) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possivel cadastrar cliente, usuario ao qual o cliente pertence não está cadastrado no banco."
                );
            }

            if (nomeClienteExistente != null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar novo cliente, um cliente com este nome já está cadastrado"
                );
            }

            if (telefoneClienteExistente != null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar novo cliente, um cliente com este numero de telefone já está cadastrado"
                );
            }

            clienteRepository.save(cliente);
    }
}
