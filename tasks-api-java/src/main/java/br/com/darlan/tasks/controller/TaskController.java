package br.com.darlan.tasks.controller;


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
    public ResponseEntity<List<TaskDTO>> list() {
        List<TaskDTO> dtos = taskUtil.entityListToDTOList(service.list());
        dtos = taskUtil.addSelfLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{idTask}")
    public ResponseEntity<TaskDTO> findById(@PathVariable("idTask") Long idTask) {
        return ResponseEntity.ok(taskUtil.entityToDTO(service.findById(idTask)));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> save(@RequestBody TaskDTO dto) {
        return ResponseEntity.ok(taskUtil.entityToDTO(service.save(taskUtil.dtoToEntity(dto))));
    }

    @PutMapping("/{idTask}")
    public ResponseEntity<TaskDTO> update(@PathVariable("idTask") Long idTask, @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(taskUtil.entityToDTO(service.update(idTask, taskUtil.dtoToEntity(dto))));
    }

    @DeleteMapping("/{idTask}")
    public ResponseEntity<String> delete(@PathVariable("idTask") Long idTask) {
        service.delete(idTask);
        return ResponseEntity.ok("Task successfully deleted");
    }
}
