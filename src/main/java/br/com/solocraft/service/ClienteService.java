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

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente buscarClientePorNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    public void cadastrarCliente(ClienteRequestDTO clienteRequestDTO){
            String verificarNomeClienteASerAdicionado = clienteRequestDTO.getNome();
            Cliente clienteExistente = buscarClientePorNome(verificarNomeClienteASerAdicionado);
            if (clienteExistente != null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Não foi possivel cadastrar novo cliente, um cliente com este nome já está cadastrado"
                );
            } else {
                Cliente clienteEntity = ClienteConverter.converterDTOParaEntidade(clienteRequestDTO);
                clienteRepository.save(clienteEntity);
            }
    }
}
