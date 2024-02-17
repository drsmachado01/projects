package br.com.darlan.bfftaskskotlin.client

import br.com.darlan.bfftaskskotlin.aspect.annotations.LogExecution
import br.com.darlan.bfftaskskotlin.model.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "UserClient", url = "http://localhost:9080/users")
interface BFFUserClient {
    @GetMapping("/{idUser}")
    @LogExecution
    fun findById(@PathVariable("idUser") idUser: Long): User
}