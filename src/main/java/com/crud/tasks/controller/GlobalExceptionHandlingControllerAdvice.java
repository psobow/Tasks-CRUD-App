package com.crud.tasks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


// co to jest ControllerAdvice
// wczesniej dałem samo Controller i nie działało ?
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {
/*


    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such task")  // 404
    @ExceptionHandler(TaskNotFoundException.class)
    public void function(){
    }




    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseBody ErrorInfo
    handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex);
    }
*/
}
