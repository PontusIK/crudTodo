package org.campusmolndal.todoapi.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "todos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id
    private Long id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private LocalDate deadLine;
    private boolean completed;

    public Todo setId(Long id) {
        this.id = id;
        return this;
    }
}
