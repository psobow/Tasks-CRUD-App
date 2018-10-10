package com.crud.tasks.controller;


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

    //@RequestMapping(method = RequestMethod.GET, value = "getTasks")
    @GetMapping
    public List<TaskDto> getTasks(){
        return taskMapper.mapToTaskDtoList(dbService.getAllTasks());
    }

    //@RequestMapping(method = RequestMethod.GET, value = "getTask")
    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable("id") Long id){
        return taskMapper.mapToTaskDto(dbService.getTaskById(id));
    }

    //@RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    @DeleteMapping
    public void deleteTask(Long taskId){

    }

    //@RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    @PutMapping
    public TaskDto updateTask(TaskDto taskDto){
        return new TaskDto(1L, "Edited title", "Edited content");
    }

    //@RequestMapping(method = RequestMethod.POST, value = "createTask")
    @PostMapping
    public void createTask(TaskDto taskDto){

    }
}
