package br.com.darlan.bfftaskskotlin.client

import br.com.darlan.bfftaskskotlin.model.Task
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "TaskClient", url = "http://localhost:8080/tasks")
interface BFFTaskClient {
    @GetMapping
    fun list(): ResponseEntity<List<Task>>
}