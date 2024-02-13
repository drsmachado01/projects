package br.com.darlan.tasks.utils

import br.com.darlan.tasks.controller.TaskController
import br.com.darlan.tasks.dto.TaskDTO
import br.com.darlan.tasks.model.Task
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Component
class TaskUtil {
    fun entityToDto(entity: Task): TaskDTO {
        return TaskDTO(entity.idTask, entity.taskName, entity.taskDescription,
            entity.uniqueExecution, entity.startDate, entity.startTime, entity.endDate,
            entity.endTime, entity.address, entity.host)
    }

    fun dtoToEntity(dto: TaskDTO): Task {
        return Task(dto.idTask, dto.taskName, dto.taskDescription,
            dto.uniqueExecution, dto.startDate, dto.startTime, dto.endDate,
            dto.endTime, dto.address, dto.host)
    }

    fun entityListToDTOList(entities: List<Task>): List<TaskDTO> {
        return entities.stream().map { e -> entityToDto(e) }
            .toList()
    }

    fun dtoListToEntityList(dtos: List<TaskDTO>): List<Task> {
        return dtos.stream().map { d -> dtoToEntity(d) }
            .toList()
    }

    fun addSelfLink(dtos: List<TaskDTO>): List<TaskDTO> {
        return dtos.stream().peek { d -> run {
            d.add(linkTo(methodOn(TaskController::class.java).findById(d.idTask)).withSelfRel())
        } }.toList()
    }
}