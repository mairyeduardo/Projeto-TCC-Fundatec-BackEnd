package br.com.solocraft.controller;

import br.com.solocraft.model.dto.TaskRequestDTO;
import br.com.solocraft.model.dto.TaskResponseDTO;
import br.com.solocraft.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/servico")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> novoServico (@RequestBody TaskRequestDTO taskRequestDTO){
        return ResponseEntity.ok(taskService.adicionarServico(taskRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listarTarefas() {
        return ResponseEntity.ok(this.taskService.buscarTodasTarefas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> removerTarefaPorId(@PathVariable("id") Long id) {
        TaskResponseDTO taskRemovido = taskService.removerPorId(id);
        return ResponseEntity.ok(taskRemovido);
    }
}
