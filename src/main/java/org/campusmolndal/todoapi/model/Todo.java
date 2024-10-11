package org.campusmolndal.todoapi.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Todo {
    @Id
    private Long id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private LocalDate deadLine;
    private boolean completed;
}
