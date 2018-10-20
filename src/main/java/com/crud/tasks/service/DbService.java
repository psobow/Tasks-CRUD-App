package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    /* PYTANIE:
    w metodzie getTaskById używamy wyrażenia lambda do wyrzucenia wyjątków to oznacza że obiekt typu TaskNotFoundException
    zostanie utworzony dopiero wtedy kiedy to: repository.findById(taskId) zwróci coś innego niż obiekt typu Task

    czy takie podejście z lambdą jest lepsze niż to co zrobiłem w metodzie deleteTaskById ??
     */
    public Task getTaskById(final Long taskId) {
        return repository.findById(taskId).orElseThrow( () -> new TaskNotFoundException("Could not find task with ID: " + taskId.toString()));
    }

    public Task saveTask(final Task task){
        return repository.save(task);
    }

    public void deleteTaskById(final Long taskId) {
        Integer howManyDeleted = repository.deleteById(taskId);
        if (howManyDeleted == 0) {
            throw new TaskNotFoundException("Could not delete task with ID: " + taskId.toString());
        }
    }
}
