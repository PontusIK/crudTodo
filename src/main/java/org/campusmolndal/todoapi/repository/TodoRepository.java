package org.campusmolndal.todoapi.repository;

import org.campusmolndal.todoapi.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
