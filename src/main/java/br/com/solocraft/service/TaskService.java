package br.com.solocraft.service;

import br.com.solocraft.model.Task;
import br.com.solocraft.model.dto.TaskRequestDTO;
import br.com.solocraft.model.dto.TaskResponseDTO;
import br.com.solocraft.model.dto.converter.TaskConverter;
import br.com.solocraft.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    public  UsuarioService usuarioService;
    public ClienteService clienteService;

    public TaskService (TaskRepository taskRepository, UsuarioService usuarioService, ClienteService clienteService) {
        this.taskRepository = taskRepository;
        this.usuarioService = usuarioService;
        this.clienteService = clienteService;
    }

    public List<TaskResponseDTO> buscarTodasTarefas() {
        List<Task> tarefas = taskRepository.findAll();
        List<TaskResponseDTO> taskResponse = new ArrayList<>();

        for (Task t : tarefas) {
            taskResponse.add(TaskConverter.converterEntidadeParaDTO(t));
        }
        return taskResponse;
    }

    public TaskResponseDTO adicionarServico(TaskRequestDTO taskRequestDTO) {

        var buscandoClienteBanco = clienteService.buscarClientePorNome(taskRequestDTO.getCliente().getNome());
        var usuario  = usuarioService.buscarUsuarioPorId(taskRequestDTO.getIdUsuario());

        String tituloASerAdicionado = taskRequestDTO.getTitulo();
        BigDecimal valorDoServico = taskRequestDTO.getValorServico();
        BigDecimal valorInicial = taskRequestDTO.getCustoInicial();


        if (Objects.isNull(buscandoClienteBanco)) {
            clienteService.cadastrarCliente(taskRequestDTO.getCliente());
        }

        if (Objects.isNull(valorDoServico)){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Insira um Orçamento para o serviço"
            );
        }

        if (Objects.isNull(tituloASerAdicionado)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insira um titulo para o serviço"
            );
        }


        if (Objects.isNull(usuario)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possivel fechar pedido, usuario não cadastrado no banco."
            );
        }

        Task task = TaskConverter.converterDTOParaEntidade(taskRequestDTO, usuario);
        taskRepository.save(task);

        return TaskConverter.converterEntidadeParaDTO(task);
    }

}
