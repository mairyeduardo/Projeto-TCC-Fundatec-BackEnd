package br.com.solocraft.service;

import br.com.solocraft.model.Cliente;
import br.com.solocraft.model.Task;
import br.com.solocraft.model.dto.ClienteResponseDTO;
import br.com.solocraft.model.dto.TaskResponseDTO;
import br.com.solocraft.model.dto.converter.ClienteConverter;
import br.com.solocraft.model.dto.converter.TaskConverter;
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

    public Cliente buscarClientePorId(Long id) {
        var cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    public List<ClienteResponseDTO> buscarClientePorIdDoUsuario(Long id) {
        var usuarioEncontrado = usuarioService.buscarUsuarioPorId(id);
        List<Cliente> clienteEncontrado = clienteRepository.findByUsuario(usuarioEncontrado);
        List<ClienteResponseDTO> clienteResponseDTO = new ArrayList<>();

        if (clienteEncontrado.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Clientes nao encontrado para usuario de id: " + id);
        }

        for (Cliente c: clienteEncontrado) {
            clienteResponseDTO.add(ClienteConverter.converterEntidadeParaDTO(c));
        }

        return clienteResponseDTO;
    }

    public ClienteResponseDTO buscarClientePorNomeUtilizandoIdDoUsuario(Long id, String nome) {
        var usuarioEncontrado = usuarioService.buscarUsuarioPorId(id);
        List<Cliente> verificarSeUsuarioPossuiClientes = clienteRepository.findByUsuario(usuarioEncontrado);

        if (Objects.isNull(usuarioEncontrado)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nenhum usuario de id: " + id + " cadastrado no banco de dados.");
        }

        if (verificarSeUsuarioPossuiClientes.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nenhum cliente encontrado para id de Usuario: " + id);
        }

        var verificarNomeCliente = clienteRepository.findByNome(nome);

        if (Objects.isNull(verificarNomeCliente)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nenhum cliente encontrado com o nome: " + nome);
        }

        for (Cliente c: verificarSeUsuarioPossuiClientes) {

                if (Objects.equals(c.getNome(), verificarNomeCliente.getNome())) {
                    return ClienteConverter.converterEntidadeParaDTO(c);
                } else {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Nenhum cliente encontrado com o nome: " + nome + "para este usuario");
                }
        }


        return null;
    }


    public Cliente buscarClientePorNome(String nome) {

        return clienteRepository.findByNome(nome);
    }

    public List<ClienteResponseDTO> buscarCliente() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponseDTO> clienteResponseDTO = new ArrayList<>();

        for (Cliente c : clientes) {
            clienteResponseDTO.add(ClienteConverter.converterEntidadeParaDTO(c));
        }

        return clienteResponseDTO;
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

            if (Objects.isNull(nomeNovoCliente)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Não é possivel cadastrar cliente, insira um nome de usuario válido."
                );
            }

            if (nomeClienteExistente != null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar novo cliente, um cliente com este nome já está cadastrado."
                );
            }

            if (Objects.isNull(telefoneNovoCliente)) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar novo cliente, Insira um telefone válido."
                );
            }

            if (telefoneClienteExistente != null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar novo cliente, um cliente com este numero de telefone já está cadastrado"
                );
            }

            clienteRepository.save(cliente);
    }

    public ClienteResponseDTO removerPorId(Long id) {

        Cliente clienteASerRemovido = buscarClientePorId(id);

        if (clienteASerRemovido == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possivel remover, Cliente de id: " + id + " não cadastrado no banco de dados.");
        } else {
            ClienteResponseDTO clienteResponseDTO = ClienteConverter.converterEntidadeParaDTO(clienteASerRemovido);
            clienteRepository.delete(clienteASerRemovido);
            return clienteResponseDTO;
        }
    }


}
