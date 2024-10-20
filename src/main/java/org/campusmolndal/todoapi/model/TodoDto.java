package org.campusmolndal.todoapi.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    @Schema(description = "Name for todo object", example = "dishes")
    private String title;

    @Schema(description = "Description for what to do", example = "do the dishes")
    private String description;

    @Schema(description = "Date by which task is to be completed", example = "2024-11-11")
    private LocalDate deadLine;
}
