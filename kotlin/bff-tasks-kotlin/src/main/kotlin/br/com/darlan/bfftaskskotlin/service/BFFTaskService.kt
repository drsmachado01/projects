package br.com.darlan.bfftaskskotlin.service

import br.com.darlan.bfftaskskotlin.aspect.annotations.LogExecution
import br.com.darlan.bfftaskskotlin.client.BFFTaskClient
import br.com.darlan.bfftaskskotlin.dto.TaskDTO
import br.com.darlan.bfftaskskotlin.util.TaskUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BFFTaskService {
    private lateinit var client: BFFTaskClient
    private lateinit var util: TaskUtil

    @Autowired
    fun TaskService(client: BFFTaskClient, util: TaskUtil) {
        this.client = client
        this.util = util
    }

    @LogExecution
    fun list(): List<TaskDTO> {
        return util.listEntityToListDTO(client.list().body!!)
    }
}