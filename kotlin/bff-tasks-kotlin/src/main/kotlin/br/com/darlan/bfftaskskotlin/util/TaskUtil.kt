package br.com.darlan.bfftaskskotlin.util

import br.com.darlan.bfftaskskotlin.aspect.annotations.LogExecution
import br.com.darlan.bfftaskskotlin.client.BFFUserClient
import br.com.darlan.bfftaskskotlin.dto.TaskDTO
import br.com.darlan.bfftaskskotlin.model.Task
import br.com.darlan.bfftaskskotlin.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TaskUtil {
    private lateinit var userClient: BFFUserClient
    private lateinit var userUtil: UserUtil
    @Autowired
    fun TaskUtil(userClient: BFFUserClient, userUtil: UserUtil) {
        this.userClient = userClient
        this.userUtil = userUtil
    }

    @LogExecution
    fun entityToDTO(entity: Task): TaskDTO {
        return TaskDTO(
            entity.idTask!!,
            entity.taskName!!,
            entity.taskDescription!!,
            entity.uniqueExecution!!,
            entity.startDate!!,
            entity.startTime!!,
            entity.endDate!!,
            entity.endTime!!,
            entity.address!!,
            null
        )
    }

    @LogExecution
    fun listEntityToListDTO(list: List<Task>): List<TaskDTO> {
        return list.stream().map { it -> enrich(it) }.toList()
    }

    @LogExecution
    fun enrich(task: Task): TaskDTO {
        val idUser: Long = task.host!!
        val user: User = userClient.findById(idUser)
        val taskDTO: TaskDTO = entityToDTO(task)
        taskDTO.host = userUtil.entityToDTO(user)
        return taskDTO
    }
}