package org.campusmolndal.todoapi.service;

import java.time.LocalDate;

import org.campusmolndal.todoapi.Exception.InvalidDateException;
import org.campusmolndal.todoapi.model.Todo;
import org.campusmolndal.todoapi.model.TodoDto;
import org.campusmolndal.todoapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepo;

    public Todo addTodo(TodoDto todo) {
        if (todo.getDeadLine().isBefore(LocalDate.now())) {
            throw new InvalidDateException("Deadline cannot be in the past");
        }
        todo.setId(null); // låt spring generera id
        todo.setCreationDate(LocalDate.now());
        todo.setCompleted(false);
    }

    public Object findTodoById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findTodoById'");
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
