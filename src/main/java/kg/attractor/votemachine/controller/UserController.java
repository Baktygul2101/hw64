package kg.attractor.votemachine.controller;

import kg.attractor.votemachine.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TaskRepository taskRepo;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {

        // merge
        User userN = userRepo.findById(user.id).orElse(user);
        for (Task t : user.tasks) {
            if (userN.tasks.stream().filter(x -> x.id.equals(t.id)).count() == 0)
                userN.tasks.add(t);
        }

        // save
        List<Task> tasks = userN.tasks;
        for (Task t : tasks)
            taskRepo.save(t);

        userRepo.save(userN);

        return userN;
    }

    @DeleteMapping("/user/{id}")
    public User deleteUser(@PathVariable String id) {
        User user = userRepo.findById(id).orElse(new User());
        userRepo.deleteById(id);

        return user;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        User user = userRepo.findById(id).orElse(new User());

        return user;
    }

}
