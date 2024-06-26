package br.com.solocraft.model.dto.converter;

import br.com.solocraft.model.Cliente;
import br.com.solocraft.model.Task;
import br.com.solocraft.model.Usuario;
import br.com.solocraft.model.dto.TaskRequestDTO;
import br.com.solocraft.model.dto.TaskResponseDTO;
import br.com.solocraft.service.ClienteService;
import br.com.solocraft.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class TaskConverter {

    public static Task converterDTOParaEntidade(TaskRequestDTO taskRequestDTO, Usuario usuario){
        Task taskEntity = new Task();
        taskEntity.setUsuario(usuario);
        taskEntity.setTitulo(taskRequestDTO.getTitulo());
        taskEntity.setDescricao(taskRequestDTO.getDescricao());
        taskEntity.setValorServico(taskRequestDTO.getValorServico());
        taskEntity.setCustoAtual(taskRequestDTO.getCustoAtual());
        taskEntity.setCustoSoma(taskRequestDTO.getCustoSoma());
        taskEntity.setDataInicio(taskRequestDTO.getDataInicio());
        taskEntity.setEnderecoServico(taskRequestDTO.getEnderecoServico());
        taskEntity.setStatusTarefa("Em_Progresso");
        taskEntity.setCliente(taskRequestDTO.getCliente());

        return taskEntity;
    }

    public static TaskResponseDTO converterEntidadeParaDTO(Task task) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setIdUsuario(task.getUsuario().getId());
        taskResponseDTO.setTitulo(task.getTitulo());
        taskResponseDTO.setDescricao(task.getDescricao());
        taskResponseDTO.setValorServico(task.getValorServico());
        taskResponseDTO.setCustoAtual(task.getCustoAtual());
        taskResponseDTO.setCustoSoma(task.getCustoSoma());
        taskResponseDTO.setDataInicio(task.getDataInicio());
        taskResponseDTO.setDataFinal(task.getDataFinal());
        taskResponseDTO.setEnderecoServico(task.getEnderecoServico());
        taskResponseDTO.setStatusTarefa(task.getStatusTarefa());
        taskResponseDTO.setNomeCliente(task.getCliente().getNome());
        taskResponseDTO.setIdCliente(task.getCliente().getId());

        return taskResponseDTO;
    }
}
