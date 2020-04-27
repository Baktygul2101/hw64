package kg.attractor.votemachine.controller;

import kg.attractor.votemachine.model.Task;
import kg.attractor.votemachine.model.TaskRepository;
import kg.attractor.votemachine.model.User;
import kg.attractor.votemachine.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepo;

    @DeleteMapping("/task/{id}")
    public Task deleteTask(@PathVariable String id) {
        Task task = taskRepo.findById(id).orElse(new Task());
        taskRepo.deleteById(id);

        return task;
    }

    @GetMapping("/task/{id}")
    public Task getTask(@PathVariable String id) {
        Task task = taskRepo.findById(id).orElse(new Task());

        return task;
    }

}
