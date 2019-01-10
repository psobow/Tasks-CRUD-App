package com.crud.tasks.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such task")  // 404
public class TaskNotFoundException extends RuntimeException  {
    public TaskNotFoundException(final String message) {
        super(message);
    }
}
