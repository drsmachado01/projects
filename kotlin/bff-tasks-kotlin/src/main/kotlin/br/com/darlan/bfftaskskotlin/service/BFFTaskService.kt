package br.com.darlan.bfftaskskotlin.service

import br.com.darlan.bfftaskskotlin.client.BFFTaskClient
import br.com.darlan.bfftaskskotlin.model.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BFFTaskService {
    private lateinit var BFFTaskClient: BFFTaskClient

    @Autowired
    fun TaskService(BFFTaskClient: BFFTaskClient) {
        this.BFFTaskClient = BFFTaskClient
    }

    fun list(): List<Task> {
        return BFFTaskClient.list().body!!
    }
}