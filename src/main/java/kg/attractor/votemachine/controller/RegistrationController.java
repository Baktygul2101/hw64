package kg.attractor.votemachine.controller;

import kg.attractor.votemachine.model.Person;
import kg.attractor.votemachine.model.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private PersonRepository repo;

    @PostMapping("/registration")
    public Person createComment(@RequestParam("email") String email, @RequestParam("name") String name,
                                @RequestParam("login") String login, @RequestParam("password") String password) {

        Person p = new Person(email, name, login, password);
        repo.save(p);

        return p;
    }

    @PostMapping("/login")
    public Person makeLogin(@RequestParam("login") String login, @RequestParam("password") String password) {

        Person p = repo.findByLogin(login).get();
        repo.save(p);

        return p;
    }

}
