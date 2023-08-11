package com.example.jirakiller.task.validators;

import jakarta.validation.constraints.NotEmpty;

public class CreateTask {
    @NotEmpty
    public String name;

    @NotEmpty
    public String description;
}
