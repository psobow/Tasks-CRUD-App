package com.crud.tasks.controller;


import com.crud.tasks.dto.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    //@RequestMapping(method = RequestMethod.GET, value = "getTasks")
    @GetMapping("getTasks")
    public List<TaskDto> getTasks(){
        return new ArrayList<>();
    }

    //@RequestMapping(method = RequestMethod.GET, value = "getTask")
    @GetMapping("getTask")
    public TaskDto getTask(Long taskId){
        return new TaskDto(1L,"test title", "test content");
    }

    //@RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    @DeleteMapping("deleteTask")
    public void deleteTask(Long taskId){

    }

    //@RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    @PutMapping("updateTask")
    public TaskDto updateTask(TaskDto taskDto){
        return new TaskDto(1L, "Edited title", "Edited content");
    }

    //@RequestMapping(method = RequestMethod.POST, value = "createTask")
    @PostMapping("createTask")
    public void createTask(TaskDto taskDto){

    }
}
