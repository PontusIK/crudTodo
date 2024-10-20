package org.campusmolndal.todoapi.controller;

import java.util.List;

import org.campusmolndal.todoapi.model.Todo;
import org.campusmolndal.todoapi.model.TodoDto;
import org.campusmolndal.todoapi.service.TodoService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "create a todo object")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successfully created todo object"),
        @ApiResponse(responseCode = "400", description = "invalid deadLine")
    })
    @PostMapping("")
    public ResponseEntity<Todo> createTodo(@RequestBody TodoDto todo) {
        return ResponseEntity.ok(todoService.addTodo(todo));
    }

    @Operation(summary = "fetch a todo object by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successfully found todo object with given id"),
        @ApiResponse(responseCode = "404", description = "unable to find todo object with given id")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Todo> readTodo(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.findTodoById(id));
    }

    @Operation(summary = "fetch all todo objects")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "list with all todo objects found"),
        @ApiResponse(responseCode = "404", description = "found 0 todo objects")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Todo>> readAllTodos() {
        return ResponseEntity.ok(todoService.findAllTodos());
    }

    @Operation(summary = "update todo object by reference to supplied todo object")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successfully updated todo object"),
        @ApiResponse(responseCode = "404", description = "todo object with supplied id not found"),
        @ApiResponse(responseCode = "400", description = "invalid deadline")
    })
    @PutMapping("")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.updateTodo(todo));
    }

    @Operation(summary = "delete todo object with given id")
    @ApiResponse(responseCode = "204", description = "deleted todo object if found, ignored otherwise")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
