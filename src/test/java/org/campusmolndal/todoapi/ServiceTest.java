package org.campusmolndal.todoapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.campusmolndal.todoapi.model.Todo;
import org.campusmolndal.todoapi.model.TodoDto;
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
        TodoDto dto = new TodoDto(
            "title",
            "description",
            LocalDate.now().plusDays(10) // deadLine
        );

        Todo todoExpected = new Todo(
            null,
            dto.getTitle(),
            dto.getDescription(),
            LocalDate.now(),
            dto.getDeadLine(),
            false
        );

        when(todoRepo.save(any(Todo.class))).thenAnswer(invocation -> {
            Todo todoToSave = invocation.getArgument(0);
            return todoToSave.setId(1L);
        });

        Todo todoActual = todoService.addTodo(dto);
        
        todoExpected.setId(1L);
        assertEquals(todoExpected, todoActual);
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
