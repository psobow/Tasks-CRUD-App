package com.crud.tasks.controller;


import com.crud.tasks.dto.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/v1/tasks")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TaskController {
    private final DbService dbService;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTasksDto(dbService.getAllTasks());
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable("id") final Long taskId) {
        return taskMapper.mapToTaskDto(dbService.getTaskById(taskId));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") final Long taskId) {
        dbService.deleteTaskById(taskId);
    }

    //Dla tej metody ID pochodzi od clienta
    @PutMapping
    public TaskDto updateTask(@Valid @RequestBody final TaskDto taskDto) {
        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
    }

    //Dla tej metody ID pochodzi od serwera
    @PostMapping
    public void createTask(@Valid @RequestBody final TaskDto taskDto) {
        dbService.saveTask(taskMapper.mapToTask(taskDto));
    }

}
