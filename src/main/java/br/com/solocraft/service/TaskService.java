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
import java.util.Objects;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    public  UsuarioService usuarioService;
    public ClienteService clienteService;
    public TaskConverter taskConverter;

    public TaskService (TaskRepository taskRepository, UsuarioService usuarioService, ClienteService clienteService, TaskConverter taskConverter) {
        this.taskRepository = taskRepository;
        this.usuarioService = usuarioService;
        this.clienteService = clienteService;
        this.taskConverter = taskConverter;
    }

    public Task buscarTarefaPorTitulo (String titulo) {
        return taskRepository.findByTitulo(titulo);
    }

    public TaskResponseDTO adicionarServico(TaskRequestDTO taskRequestDTO) {

        var verificandoClienteExistente = clienteService.buscarClientePorNome(taskRequestDTO.getCliente().getNome());
        var usuario  = usuarioService.buscarUsuarioPorId(taskRequestDTO.getIdUsuario());

        String tituloASerAdicionado = taskRequestDTO.getTitulo();
        BigDecimal valorDoServico = taskRequestDTO.getValorServico();
        var clienteAdicionadoATarefa = taskRequestDTO.getCliente();

        if (clienteAdicionadoATarefa != verificandoClienteExistente) {
            clienteService.cadastrarCliente(clienteAdicionadoATarefa);
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

        Task task = taskConverter.converterDTOParaEntidade(taskRequestDTO, usuario);
        taskRepository.save(task);

        return taskConverter.converterEntidadeParaDTO(task);
    }



}
