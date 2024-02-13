package br.com.darlan.tasks.controller

import br.com.darlan.tasks.dto.TaskDTO
import br.com.darlan.tasks.service.TaskService
import br.com.darlan.tasks.utils.TaskUtil
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
    private lateinit var taskUtil: TaskUtil

    @Autowired
    fun TaskController(service: TaskService, taskUtil: TaskUtil) {
        this.service = service
        this.taskUtil = taskUtil
    }

    @GetMapping
    fun list(): ResponseEntity<List<TaskDTO>> {
        var dtos = taskUtil.entityListToDTOList(
            service.list()
        )
        dtos = taskUtil.addSelfLink(dtos)
        return ResponseEntity.ok(dtos)
    }

    @PostMapping
    fun save(@RequestBody dto: TaskDTO): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(taskUtil.entityToDto(
            service.save(taskUtil.dtoToEntity(dto))
        ))
    }

    @GetMapping("/{idTask}")
    fun findById(@PathVariable("idTask") idTask: Long): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(taskUtil.entityToDto(
            service.findById(idTask)
        ))
    }

    @PutMapping("/{idTask}")
    fun update(@PathVariable("idTask") idTask: Long, @RequestBody dto: TaskDTO): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(taskUtil.entityToDto(
            service.update(idTask, taskUtil.dtoToEntity(dto))
        ))
    }

    @DeleteMapping("/{idTask}")
    fun delete(@PathVariable("idTask") idTask: Long): ResponseEntity<String> {
        service.delete(idTask)
        return ResponseEntity.ok("Task deleted")
    }
}