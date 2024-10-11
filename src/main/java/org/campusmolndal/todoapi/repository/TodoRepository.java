package org.campusmolndal.todoapi.repository;

import org.campusmolndal.todoapi.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, Long> {

}
