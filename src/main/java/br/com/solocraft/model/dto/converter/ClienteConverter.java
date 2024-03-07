package br.com.solocraft.model.dto.converter;

import br.com.solocraft.model.Cliente;
import br.com.solocraft.model.Usuario;
import br.com.solocraft.model.dto.ClienteRequestDTO;
import br.com.solocraft.model.dto.ClienteResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ClienteConverter {

    public static Cliente converterDTOParaEntidade(ClienteRequestDTO clienteRequestDTO, Usuario usuario){
        Cliente clienteEntity = new Cliente();

        clienteEntity.setUsuario(usuario);
        clienteEntity.setNome(clienteRequestDTO.getNome());
        clienteEntity.setTelefone(clienteRequestDTO.getTelefone());
        clienteEntity.setEnderecoPrincipal(clienteRequestDTO.getEnderecoPrincipal());

        return clienteEntity;
    }

    public static ClienteResponseDTO converterEntidadeParaDTO(Cliente cliente){
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();

        clienteResponseDTO.setId(cliente.getId());
        clienteResponseDTO.setIdUsuario(cliente.getUsuario().getId());
        clienteResponseDTO.setNome(cliente.getNome());
        clienteResponseDTO.setTelefone(cliente.getTelefone());
        clienteResponseDTO.setEnderecoPrincipal(cliente.getEnderecoPrincipal());

        return clienteResponseDTO;
    }
}
