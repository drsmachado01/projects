package br.com.darlan.bfftaskskotlin.util

import br.com.darlan.bfftaskskotlin.dto.TaskDTO
import br.com.darlan.bfftaskskotlin.model.Task

class TaskUtil {
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
            entity.host!!
        )
    }
}