package br.com.darlan.kotlinusersapi.controller

import br.com.darlan.kotlinusersapi.aspect.annotations.LogExecution
import br.com.darlan.kotlinusersapi.dto.UserDTO
import br.com.darlan.kotlinusersapi.service.UserService
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
@RequestMapping("/users")
class UserController {
    private lateinit var service: UserService

    @Autowired
    fun UserController(service: UserService) {
        this.service = service
    }

    @GetMapping
    @LogExecution
    fun list(): ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok(service.list())
    }

    @GetMapping("/{id}")
    @LogExecution
    fun findById(@PathVariable("id") id: Long): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(service.findById(id))
    }

    @PostMapping
    @LogExecution
    fun save(@RequestBody dto: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(service.save(dto))
    }

    @PutMapping("/{id}")
    @LogExecution
    fun update(@PathVariable("id") id: Long, @RequestBody dto: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(service.update(id, dto))
    }

    @DeleteMapping("/{id}")
    @LogExecution
    fun delete(@PathVariable("id") id: Long): ResponseEntity<String> {
        service.delete(id)
        return ResponseEntity.ok("User deleted successfully")
    }
}