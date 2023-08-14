package com.example.jirakiller.task;
import com.example.jirakiller.task.validators.CreateTask;
import com.example.jirakiller.task.validators.SetDone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService implements CrudRepository<Task, Long> {
    private final ArrayList<Task> tasksList = new ArrayList<>();
    private long idIncrement = 1;

    public <S extends Task> S save (S task) {
        task.setId(idIncrement);
        tasksList.add(task);
        idIncrement++;
        return null;
    }

    public <S extends Task> Iterable<S> saveAll (Iterable<S> tasks) {
        // TODO
        return null;
    }

    public Optional<Task> findById (Long id) {
        return tasksList.stream().filter(task -> Objects.equals(task.getId(), id)).findFirst();
    }

    public boolean existsById (Long id) {
        return tasksList.stream().anyMatch(task -> Objects.equals(task.getId(), id));
    }

    public Iterable<Task> findAll () {
        return tasksList;
    }

    public Iterable<Task> findAll (boolean doneOnly) {
        if (doneOnly) {
            return tasksList.stream().filter(Task::getIsDone).toList();
        }

        return tasksList;
    }

    public Iterable<Task> findAllById (Iterable<Long> ids) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        ids.forEach(id -> {
            Optional<Task> task = this.findById(id);
            if (task.isPresent()) {
                foundTasks.add(this.findById(id).get());
            }
        });

        return foundTasks;
    }

    public long count () {
        return tasksList.size();
    }

    public void deleteById (Long id) {
        Optional<Task> task = this.findById(id);
        task.ifPresent(this::delete);
    }

    public void delete (Task task) {
        tasksList.remove(task);
    }

    public void deleteAllById (Iterable<? extends Long> ids) {
        // TODO
    }

    public void deleteAll (Iterable<? extends Task> tasks) {
        // TODO
    }

    public void deleteAll () {
        tasksList.clear();
    }

    public boolean create (CreateTask body) {
        try {
            Task task = new Task(body.name, body.description);
            this.save(task);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setDone (Long id, SetDone body) {
        Optional<Task> task = this.findById(id);
        task.ifPresent(value -> value.setIsDone(body.status));

        return task.isPresent();
    }
}
