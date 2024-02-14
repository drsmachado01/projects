package br.com.darlan.tasks.service

import br.com.darlan.tasks.aspect.annotations.LogExecution
import br.com.darlan.tasks.model.Task
import br.com.darlan.tasks.repository.TaskRepository
import br.com.darlan.tasks.service.exception.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskService {
    private lateinit var repo: TaskRepository

    @Autowired
    fun TaskService(repo: TaskRepository) {
        this.repo = repo
    }

    @LogExecution
    fun save(task: Task): Task {
        return repo.save(task)
    }

    @LogExecution
    fun list(): List<Task> {
        return repo.findAll()
    }

    @LogExecution
    fun findById(idTask: Long): Task {
        return repo.findById(idTask).orElseThrow {
            NotFoundException("There's no task associated to the id $idTask")
        }!!
    }

    @LogExecution
    fun update(idTask: Long, task: Task): Task {
        repo.findById(idTask).orElseThrow {
            NotFoundException("There's no task associated to the id $idTask")
        }
        task.idTask = idTask
        return repo.save(task)
    }

    fun delete(idTask: Long) {
        repo.delete(repo.findById(idTask).orElseThrow {
            NotFoundException("There's no task associated to the id $idTask")
        }!!)
    }
}