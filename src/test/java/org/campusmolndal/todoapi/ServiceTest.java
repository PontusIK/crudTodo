package org.campusmolndal.todoapi;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.campusmolndal.todoapi.model.Todo;
import org.campusmolndal.todoapi.repository.TodoRepository;
import org.campusmolndal.todoapi.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServiceTest {
    TodoRepository todoRepo;
    TodoService todoService;

    @BeforeEach
    void setup() {
        todoRepo = mock(TodoRepository.class);
        todoService = new TodoService(todoRepo);
    }

    @Test
    void createTodo() {
        
    }

    @Test
    void readTodo() {

    }

    @Test
    void readAllTodos() {

    }

    @Test
    void updateTodo() {

    }
}
