package org.campusmolndal.todoapi.service;

import java.time.LocalDate;

import org.campusmolndal.todoapi.exception.InvalidDateException;
import org.campusmolndal.todoapi.exception.ResourceNotFoundException;
import org.campusmolndal.todoapi.model.Todo;
import org.campusmolndal.todoapi.model.TodoDto;
import org.campusmolndal.todoapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepo;

    public Todo addTodo(TodoDto dto) {
        if (dto.getDeadLine().isBefore(LocalDate.now())) {
            throw new InvalidDateException("Deadline cannot be in the past");
        }
        Todo todo = new Todo(
            null,
            dto.getTitle(),
            dto.getDescription(),
            LocalDate.now(),
            dto.getDeadLine(),
            false
        );
        return todoRepo.save(todo);
    }

    public Todo findTodoById(Long id) {
        return todoRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    public Object findAllTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllTodos'");
    }

    public Object updateTodo(Long id, Todo todo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTodo'");
    }

    public void deleteTodo(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTodo'");
    }
}
