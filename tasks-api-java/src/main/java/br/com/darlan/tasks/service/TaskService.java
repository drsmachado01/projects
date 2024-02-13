package br.com.darlan.tasks.service;

import br.com.darlan.tasks.model.Task;
import br.com.darlan.tasks.repository.TaskRepository;
import br.com.darlan.tasks.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    @Autowired
    public TaskService(final TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> list() {
        return repository.findAll();
    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public Task findById(Long idTask) {
        return repository.findById(idTask).orElseThrow(
                () -> new NotFoundException("There's no task associated to the id " + idTask));
    }

    public Task update(Long idTask, Task task) {
        Task task_ = repository.findById(idTask).orElseThrow(
                () -> {
                    return new NotFoundException("There's no task associated to the id " + idTask);
                });
        task.setIdTask(task_.getIdTask());
        return repository.save(task);
    }

    public void delete(Long idTask) {
        Task task_ = repository.findById(idTask).orElseThrow(
                () -> new NotFoundException("There's no task associated to the id $idTask"));
        repository.deleteById(idTask);
    }
}

