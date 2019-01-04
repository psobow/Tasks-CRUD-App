package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.dto.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService dbService;
    @MockBean
    private TaskMapper taskMapper;

    private final String TASK_ENDPOINT = "/v1/tasks";


    @Test
    public void shouldFetchEmptyList() throws Exception {
        // Given
        List<TaskDto> tasksDto = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTasksDto(tasks)).thenReturn(tasksDto);

        // When & Then
        mockMvc.perform(get(TASK_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTaskList() throws Exception {
        // Given
        List<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(new TaskDto(1L, "Test title", "Test content"));

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Test title", "Test content"));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTasksDto(tasks)).thenReturn(tasksDto);

        // When & Then
        mockMvc.perform(get(TASK_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                // TaskDto fields
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test title")))
                .andExpect(jsonPath("$[0].content", is("Test content")));
    }

    @Test
    public void shouldFetchTaskListVersion2() throws Exception {
        // Given
        List<TaskDto> tasksDto = new ArrayList<>();
        tasksDto.add(new TaskDto(1L, "Test title", "Test content"));

        when(dbService.getAllTasks()).thenReturn(new ArrayList<>());
        when(taskMapper.mapToTasksDto(anyObject())).thenReturn(tasksDto);

        // When & Then
        mockMvc.perform(get(TASK_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                // TaskDto fields
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test title")))
                .andExpect(jsonPath("$[0].content", is("Test content")));
    }

    @Test
    public void shouldFetchOneTask() throws Exception {
        // Given
        final String TASK_ID = "/1";
        TaskDto taskDto = new TaskDto(1L, "Test title", "Test content");

        when(dbService.getTaskById(anyLong())).thenReturn(new Task());
        when(taskMapper.mapToTaskDto(anyObject())).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(get(TASK_ENDPOINT + TASK_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // TaskDto fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }

    @Test
    public void shouldDeleteOneTask() throws Exception {
        // Given
        final String TASK_ID = "/1";

        //Metoda deleteTaskById w klasie dbService zwraca voida więc trzeba zrobić tak:
        doNothing().when(dbService).deleteTaskById(anyLong());

        // When & Then
        mockMvc.perform(delete(TASK_ENDPOINT + TASK_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(dbService, times(1)).deleteTaskById(anyLong());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test title", "Test content");

        when(taskMapper.mapToTask(anyObject())).thenReturn(new Task());
        when(dbService.saveTask(anyObject())).thenReturn(new Task());
        when(taskMapper.mapToTaskDto(anyObject())).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(put(TASK_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                // TaskDto fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.content", is("Test content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test title", "Test content");

        when(taskMapper.mapToTask(anyObject())).thenReturn(new Task());
        when(dbService.saveTask(anyObject())).thenReturn(new Task());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(post(TASK_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent));

        verify(dbService, times(1)).saveTask(anyObject());
    }
}
