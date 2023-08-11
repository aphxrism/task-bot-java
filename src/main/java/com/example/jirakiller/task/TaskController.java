package com.example.jirakiller.task;
import com.example.jirakiller.task.validators.CreateTask;
import com.example.jirakiller.task.validators.SetDone;
import com.example.jirakiller.task.validators.TaskFindAllById;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Validated
@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    ResponseEntity<Iterable<Task>> findAll (@RequestParam(value = "doneOnly", required = false) boolean doneOnly) {
        return new ResponseEntity<>(taskService.findAll(doneOnly), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Task>> findById (@PathVariable("id") Long id) {
        return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/search-by-id")
    ResponseEntity<Iterable<Task>> findAllById (@Valid @RequestBody TaskFindAllById body) {
        return new ResponseEntity<>(taskService.findAllById(body.tasksIds), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Boolean> create (@Valid @RequestBody CreateTask body) {
        return new ResponseEntity<>(taskService.create(body), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete (@PathVariable("id") Long id) {
        try {
            taskService.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/status")
    ResponseEntity<Boolean> setDone (@PathVariable("id") Long id, @RequestBody SetDone body) {
        return new ResponseEntity<>(taskService.setDone(id, body), HttpStatus.OK);
    }
}
