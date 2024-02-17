package br.com.darlan.bfftaskskotlin.controller

import br.com.darlan.bfftaskskotlin.aspect.annotations.LogExecution
import br.com.darlan.bfftaskskotlin.dto.TaskDTO
import br.com.darlan.bfftaskskotlin.service.BFFTaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bff-task")
class BFFTaskController {
    private lateinit var service: BFFTaskService

    @Autowired
    fun BFFTaskController(service: BFFTaskService) {
        this.service = service
    }

    @GetMapping
    @LogExecution
    fun list(): ResponseEntity<List<TaskDTO>> {
        return ResponseEntity.ok(service.list())
    }
}