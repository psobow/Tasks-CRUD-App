package com.crud.tasks.controller;


import com.crud.tasks.dto.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    //@RequestMapping(method = RequestMethod.GET, value = "getTasks")
    @GetMapping
    public List<TaskDto> getTasks(){
        return new ArrayList<>();
    }

    //@RequestMapping(method = RequestMethod.GET, value = "getTask")
    @GetMapping
    public TaskDto getTask(Long taskId){
        return new TaskDto(1L,"test title", "test content");
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
