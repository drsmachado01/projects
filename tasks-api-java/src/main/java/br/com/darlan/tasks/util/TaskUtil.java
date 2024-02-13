package br.com.darlan.tasks.util;

import br.com.darlan.tasks.controller.TaskController;
import br.com.darlan.tasks.dto.TaskDTO;
import br.com.darlan.tasks.model.Task;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Component
public class TaskUtil {
    public TaskDTO entityToDTO(Task entity) {
        return new TaskDTO(entity.getIdTask(), entity.getTaskName(), entity.getTaskDescription(),
                entity.getUniqueExecution(), entity.getStartDate(), entity.getStartTime(),
                entity.getEndDate(), entity.getEndTime(), entity.getAddress(), entity.getHost());
    }

    public Task dtoToEntity(TaskDTO dto) {
        return Task.builder()
                .idTask(dto.getIdTask())
                .taskName(dto.getTaskName())
                .taskDescription(dto.getTaskDescription())
                .uniqueExecution(dto.getUniqueExecution())
                .startDate(dto.getStartDate())
                .startTime(dto.getStartTime())
                .endDate(dto.getEndDate())
                .endTime(dto.getEndTime())
                .address(dto.getAddress())
                .host(dto.getHost())
                .build();
    }

    public List<TaskDTO> entityListToDTOList(List<Task> entities) {
        return entities.stream().map(this::entityToDTO).toList();
    }

    public List<Task> dtoListToEntityList(List<TaskDTO> dtos) {
        return dtos.stream().map(this::dtoToEntity).toList();
    }

    public List<TaskDTO> addSelfLink(List<TaskDTO> tasks) {
        return tasks.stream().peek(this::addSelfLink).toList();
    }

    public TaskDTO addSelfLink(TaskDTO t) {
        return t.add(linkTo(methodOn(TaskController.class).findById(t.getIdTask())).withSelfRel());
    }
}
