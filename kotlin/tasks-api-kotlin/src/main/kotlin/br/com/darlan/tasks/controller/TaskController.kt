package br.com.darlan.tasks.controller

import br.com.darlan.tasks.dto.TaskDTO
import br.com.darlan.tasks.service.TaskService
import br.com.darlan.tasks.utils.TaskConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController {
    private lateinit var service: TaskService
    private lateinit var converter: TaskConverter

    @Autowired
    fun TaskController(service: TaskService, converter: TaskConverter) {
        this.service = service
        this.converter = converter
    }

    @GetMapping
    fun list(): ResponseEntity<List<TaskDTO>> {
        return ResponseEntity.ok(converter.entityListToDTOList(
            service.list()
        ))
    }

    @PostMapping
    fun save(@RequestBody dto: TaskDTO): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(converter.entityToDto(
            service.save(converter.dtoToEntity(dto))
        ))
    }

    @GetMapping("/{idTask}")
    fun findById(@PathVariable("idTask") idTask: Long): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(converter.entityToDto(
            service.findById(idTask)
        ))
    }

    @PutMapping("/{idTask}")
    fun update(@PathVariable("idTask") idTask: Long, @RequestBody dto: TaskDTO): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(converter.entityToDto(
            service.update(idTask, converter.dtoToEntity(dto))
        ))
    }

    @DeleteMapping("/{idTask}")
    fun delete(@PathVariable("idTask") idTask: Long): ResponseEntity<String> {
        service.delete(idTask)
        return ResponseEntity.ok("Task deleted")
    }
}