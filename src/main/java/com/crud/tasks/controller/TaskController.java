package com.crud.tasks.controller;


import com.crud.tasks.domain.Task;
import com.crud.tasks.dto.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    @Autowired
    private DbService dbService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> getTasks(){
        return taskMapper.mapToTaskDtoList(dbService.getAllTasks());
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable("id") final Long taskId){
        return taskMapper.mapToTaskDto(dbService.getTaskById(taskId));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") final Long taskId){
        dbService.deleteTaskById(taskId);
    }

    @PutMapping
    public TaskDto updateTask(@RequestBody final TaskDto taskDto){
        return new TaskDto(1L, "Edited title", "Edited content");
    }

    @PostMapping()
    public void createTask( @RequestBody final TaskDto taskDto ){
        dbService.saveTask(taskMapper.mapToTask(taskDto));
    }

}
