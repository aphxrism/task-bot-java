package com.example.jirakiller.task.validators;

import jakarta.validation.constraints.Digits;

public class TaskFindAllById {
    @Digits.List({})
    public Iterable<Long> tasksIds;
}
