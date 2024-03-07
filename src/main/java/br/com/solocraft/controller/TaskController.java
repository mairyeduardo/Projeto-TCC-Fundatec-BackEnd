package br.com.solocraft.controller;

import br.com.solocraft.model.Task;
import br.com.solocraft.model.dto.TaskRequestDTO;
import br.com.solocraft.model.dto.TaskResponseDTO;
import br.com.solocraft.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/servico")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/novo")
    public ResponseEntity<TaskResponseDTO> novoServico (@RequestBody TaskRequestDTO taskRequestDTO){
        return ResponseEntity.ok(taskService.adicionarServico(taskRequestDTO));
    }
}
