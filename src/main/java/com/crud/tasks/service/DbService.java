package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class DbService {
    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

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
