package br.com.darlan.tasks.controller;


import br.com.darlan.tasks.aspect.annotations.LogExecution;
import br.com.darlan.tasks.dto.TaskDTO;
import br.com.darlan.tasks.service.TaskService;
import br.com.darlan.tasks.util.TaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;
    private final TaskUtil taskUtil;

    @Autowired
    public TaskController(final TaskService service, final TaskUtil taskUtil) {
        this.service = service;
        this.taskUtil = taskUtil;
    }

    @GetMapping
    @LogExecution
    public ResponseEntity<List<TaskDTO>> list() {
        List<TaskDTO> dtos = taskUtil.entityListToDTOList(service.list());
        dtos = taskUtil.addSelfLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{idTask}")
    @LogExecution
    public ResponseEntity<TaskDTO> findById(@PathVariable("idTask") Long idTask) {
        return ResponseEntity.ok(taskUtil.entityToDTO(service.findById(idTask)));
    }

    @PostMapping
    @LogExecution
    public ResponseEntity<TaskDTO> save(@RequestBody TaskDTO dto) {
        return ResponseEntity.ok(taskUtil.entityToDTO(service.save(taskUtil.dtoToEntity(dto))));
    }

    @PutMapping("/{idTask}")
    @LogExecution
    public ResponseEntity<TaskDTO> update(@PathVariable("idTask") Long idTask, @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(taskUtil.entityToDTO(service.update(idTask, taskUtil.dtoToEntity(dto))));
    }

    @DeleteMapping("/{idTask}")
    @LogExecution
    public ResponseEntity<String> delete(@PathVariable("idTask") Long idTask) {
        service.delete(idTask);
        return ResponseEntity.ok("Task successfully deleted");
    }
}
