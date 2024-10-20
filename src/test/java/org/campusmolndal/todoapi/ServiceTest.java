package org.campusmolndal.todoapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.campusmolndal.todoapi.exception.InvalidDateException;
import org.campusmolndal.todoapi.exception.ResourceNotFoundException;
import org.campusmolndal.todoapi.model.Todo;
import org.campusmolndal.todoapi.model.TodoDto;
import org.campusmolndal.todoapi.repository.TodoRepository;
import org.campusmolndal.todoapi.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceTest {
    TodoRepository todoRepo;
    TodoService todoService;

    @BeforeEach
    void setup() {
        todoRepo = mock(TodoRepository.class);
        todoService = new TodoService(todoRepo);
    }

    @Test
    void createTodoHappy() {
        TodoDto dto = new TodoDto(
            "title",
            "description",
            LocalDate.now().plusDays(10) // deadLine
        );

        Todo todoExpected = new Todo(
            1L,
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

        assertEquals(todoExpected, todoActual);
    }

    @Test
    void createTodoBad() {
        TodoDto dto = new TodoDto(
            "title",
            "description",
            LocalDate.now().minusDays(10)
        );

        Exception exception = assertThrowsExactly(InvalidDateException.class, () -> {
            todoService.addTodo(dto);
        });

        String expectesMsg = "Deadline cannot be in the past";
        String actualMsg = exception.getMessage();

        assertTrue(actualMsg.contains(expectesMsg));
    }


    @Test
    void readTodo() {
        when(todoRepo.findById(1L)).thenReturn(Optional.of(
            new Todo(
                1L,
                "title",
                "description",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                false
            )
        ));

        when(todoRepo.findById(2L)).thenReturn(Optional.empty());

        Todo result = todoService.findTodoById(1L);
        Exception exception = assertThrowsExactly(ResourceNotFoundException.class, () -> {
            todoService.findTodoById(2L);
        });
        String expectesMsg = "Task not found";
        String actualMsg = exception.getMessage();

        assertTrue(actualMsg.contains(expectesMsg));
        assertEquals(1L, result.getId());
    }

    @Test
    void readAllTodosHappy() {
        when(todoRepo.findAll()).thenReturn(List.of(
            new Todo(
                1L,
                "title",
                "description",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                false
            )
        ));
        
        List<Todo> result = todoService.findAllTodos();
        assertEquals(1, result.size());
    }

    @Test
    void readAllTodosBad() {
        when(todoRepo.findAll()).thenReturn(List.of());
        
        Exception exception = assertThrowsExactly(
            ResourceNotFoundException.class,
            () -> todoService.findAllTodos()
        );
    
        String expectedMsg = "No Tasks found";
        String actualMsg = exception.getMessage();
        
        assertTrue(actualMsg.contains(expectedMsg));
    }
    @Test
    void updateTodoHappy() {
        Todo existingTodo = new Todo(
            1L,
            "original title",
            "original description",
            LocalDate.now(),
            LocalDate.now().plusDays(10),
            false
        );
        Todo updatedTodo = new Todo(
            1L,
            "updated title",
            "updated description",
            LocalDate.now(),
            LocalDate.now().plusDays(10),
            true
        );
        when(todoRepo.existsById(1L)).thenReturn(true);
        when(todoRepo.findById(1L))
            .thenReturn(Optional.of(existingTodo));
        when(todoRepo.save(any(Todo.class)))
            .thenReturn(updatedTodo);

        Todo result = todoService.updateTodo(updatedTodo);

        assertEquals(updatedTodo.getTitle(), result.getTitle());
        assertEquals(existingTodo.getCreationDate(), result.getCreationDate());
    }

    @Test
    void updateTodoNotFound() {
        Todo todo = new Todo();
        todo.setId(1L);
        when(todoRepo.existsById(1L)).thenReturn(false);

        Exception exception = assertThrowsExactly(
            ResourceNotFoundException.class,
            () -> todoService.updateTodo(todo)    
        );

        String excpectedMsg = "Task not found";
        String actualMsg = exception.getMessage();

        assertTrue(actualMsg.contains(excpectedMsg));
    }

    @Test
    void updateTodoBadDeadline() {
        Todo todo = new Todo(
            1L,
            "title",
            "description",
            LocalDate.now(),
            LocalDate.now().minusDays(10),
            false
        );
        when(todoRepo.existsById(1L)).thenReturn(true);

        Exception exception = assertThrowsExactly(
            InvalidDateException.class,
            () -> todoService.updateTodo(todo)
        );

        String expectedMsg = "Deadline cannot be in the past";
        String actualMsg = exception.getMessage();

        assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test
    void deleteTodo() {
        todoService.deleteTodo(1L);
        verify(todoRepo, times(1)).deleteById(1L);
    }
}
