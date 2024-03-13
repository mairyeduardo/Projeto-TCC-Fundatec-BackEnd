package br.com.solocraft.service;

import br.com.solocraft.model.Cliente;
import br.com.solocraft.model.Task;
import br.com.solocraft.model.dto.ClienteRequestDTO;
import br.com.solocraft.model.dto.ClienteResponseDTO;
import br.com.solocraft.model.dto.TaskRequestDTO;
import br.com.solocraft.model.dto.TaskResponseDTO;
import br.com.solocraft.model.dto.converter.ClienteConverter;
import br.com.solocraft.model.dto.converter.TaskConverter;
import br.com.solocraft.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    public UsuarioService usuarioService;
    public ClienteService clienteService;

    public TaskService(TaskRepository taskRepository, UsuarioService usuarioService, ClienteService clienteService) {
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

        var usuario = usuarioService.buscarUsuarioPorId(taskRequestDTO.getIdUsuario());
        Cliente buscarClienteExistente = clienteService.buscarClientePorNome(taskRequestDTO.getCliente().getNome());

        if (Objects.isNull(usuario)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possivel fechar pedido, usuario não cadastrado no banco."
            );
        }

        if (Objects.isNull(taskRequestDTO.getTitulo())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insira um titulo para o serviço."
            );
        }

        if (Objects.isNull(taskRequestDTO.getValorServico())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insira um Orçamento para o serviço."
            );
        }

        if (Objects.isNull(taskRequestDTO.getCustoInicial())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insira um Valor Inicial para o serviço."
            );
        }

        if (Objects.isNull(taskRequestDTO.getDataInicio())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insira uma Data Inicial para o serviço."
            );
        }

        if (taskRequestDTO.getDataInicio().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A data inicial inserida é invalida, informe uma data futura."
            );
        }

        if (Objects.isNull(taskRequestDTO.getEnderecoServico())) {
            throw new ResponseStatusException(
              HttpStatus.BAD_REQUEST,
              "Insira um endereco relacionado ao serviço."
            );
        }

        if (Objects.isNull(taskRequestDTO.getCliente())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insira um cliente relacionado ao serviço."
            );
        }

        if (Objects.isNull(buscarClienteExistente)){
            var cliente = taskRequestDTO.getCliente();
            cliente.setUsuario(usuario);
            clienteService.cadastrarCliente(cliente);
        } else {
            taskRequestDTO.setCliente(buscarClienteExistente);
        }

        Task task = TaskConverter.converterDTOParaEntidade(taskRequestDTO, usuario);
        taskRepository.save(task);

        return TaskConverter.converterEntidadeParaDTO(task);
    }

}
