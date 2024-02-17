package br.com.darlan.tasks.service

import br.com.darlan.tasks.aspect.annotations.LogExecution
import br.com.darlan.tasks.dto.TaskDTO
import br.com.darlan.tasks.repository.TaskRepository
import br.com.darlan.tasks.service.exception.NotFoundException
import br.com.darlan.tasks.utils.TaskUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskService {
    private lateinit var repo: TaskRepository
    private lateinit var util: TaskUtil

    @Autowired
    fun TaskService(repo: TaskRepository, util: TaskUtil) {
        this.repo = repo
        this.util = util
    }

    @LogExecution
    fun save(dto: TaskDTO): TaskDTO {
        return util.addSelfLink(util.entityToDto(repo.save(util.dtoToEntity(dto))))
    }

    @LogExecution
    fun list(): List<TaskDTO> {
        return util.addSelfLink(util.entityListToDTOList(repo.findAll()))
    }

    @LogExecution
    fun findById(idTask: Long): TaskDTO {
        return util.addSelfLink(util.entityToDto(repo.findById(idTask).orElseThrow {
            NotFoundException("There's no task associated to the id $idTask")
        }!!))
    }

    @LogExecution
    fun update(idTask: Long, dto: TaskDTO): TaskDTO {
        repo.findById(idTask).orElseThrow {
            NotFoundException("There's no task associated to the id $idTask")
        }
        dto.idTask = idTask
        return util.addSelfLink(util.entityToDto(repo.save(util.dtoToEntity(dto))))
    }

    fun delete(idTask: Long) {
        repo.delete(repo.findById(idTask).orElseThrow {
            NotFoundException("There's no task associated to the id $idTask")
        }!!)
    }
}