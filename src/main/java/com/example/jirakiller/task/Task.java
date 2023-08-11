package com.example.jirakiller.task;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private final String name;
    private final String description;
    private Boolean isDone = false;

    Task (String name, String description) {
        this.name = name;
        this.description = description;
    }
}
