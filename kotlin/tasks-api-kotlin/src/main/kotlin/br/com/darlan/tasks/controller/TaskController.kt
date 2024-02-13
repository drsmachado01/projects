package br.com.darlan.tasks.controller

import br.com.darlan.tasks.aspect.annotations.LogExecution
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

    @GetMapping
    @LogExecution
    fun list(): ResponseEntity<List<TaskDTO>> {
        return ResponseEntity.ok(taskUtil.addSelfLink(taskUtil.entityListToDTOList(
            service.list()
        )))
    }

    @PostMapping
    @LogExecution
    fun save(@RequestBody dto: TaskDTO): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(taskUtil.addSelfLink(taskUtil.entityToDto(
            service.save(taskUtil.dtoToEntity(dto))
        )!!))
    }

    @GetMapping("/{idTask}")
    @LogExecution
    fun findById(@PathVariable("idTask") idTask: Long): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(taskUtil.addSelfLink(taskUtil.entityToDto(
            service.findById(idTask)
        )))
    }

    @PutMapping("/{idTask}")
    @LogExecution
    fun update(@PathVariable("idTask") idTask: Long, @RequestBody dto: TaskDTO): ResponseEntity<TaskDTO> {
        return ResponseEntity.ok(taskUtil.addSelfLink(taskUtil.entityToDto(
            service.update(idTask, taskUtil.dtoToEntity(dto))
        )))
    }

    @DeleteMapping("/{idTask}")
    @LogExecution
    fun delete(@PathVariable("idTask") idTask: Long): ResponseEntity<String> {
        service.delete(idTask)
        return ResponseEntity.ok("Task deleted")
    }

    companion object {
        @Autowired
        fun TaskController(taskController: TaskController, service: TaskService, taskUtil: TaskUtil) {
            taskController.service = service
            taskController.taskUtil = taskUtil
        }
    }
}