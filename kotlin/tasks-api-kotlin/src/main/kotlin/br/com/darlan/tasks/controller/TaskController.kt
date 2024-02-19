package br.com.darlan.tasks.controller

import br.com.darlan.tasks.aspect.annotations.LogExecution
import br.com.darlan.tasks.dto.TaskDTO
import br.com.darlan.tasks.service.TaskService

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
class TaskController (@Autowired var service: TaskService){

    @GetMapping
    @LogExecution
    fun list(): ResponseEntity<List<TaskDTO>> {
        return ResponseEntity.ok(service.list())
    }

    @PostMapping
    @LogExecution
    fun save(@RequestBody dto: TaskDTO): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(service.save(dto))
    }

    @GetMapping("/{idTask}")
    @LogExecution
    fun findById(@PathVariable("idTask") idTask: Long): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(service.findById(idTask))
    }

    @PutMapping("/{idTask}")
    @LogExecution
    fun update(@PathVariable("idTask") idTask: Long, @RequestBody dto: TaskDTO): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(service.update(idTask, dto))
    }

    @DeleteMapping("/{idTask}")
    @LogExecution
    fun delete(@PathVariable("idTask") idTask: Long): ResponseEntity<String> {
        service.delete(idTask)
        return ResponseEntity.ok("Task $idTask deleted")
    }
}