package kg.attractor.votemachine.controller;

import kg.attractor.votemachine.model.Comment;
import kg.attractor.votemachine.model.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository repo;

    Map<String, Object> userMap = new HashMap<>();

    public CommentController() {
        // init user Map
        userMap.put("name", "Aibek");
        userMap.put("surname", "Askarov");
        userMap.put("gender", "MALE");
        userMap.put("birthDate", LocalDate.of(1990, 1, 1));
        userMap.put("graduated", true);
        userMap.put("gpa", 3.9);

    }

    @PostMapping("/comment")
    public Comment createComment(@RequestBody Comment comment) {

        List<Comment> childs = comment.comments;
        for (Comment c : childs)
            repo.save(c);

        repo.save(comment);

        return comment;
    }

    @DeleteMapping("/comment/{id}")
    public Comment deleteComment(@PathVariable String id) {
        Comment comment = repo.findById(id).orElse(new Comment());
        repo.deleteById(id);

        return comment;
    }

    @GetMapping("/comment/{id}")
    public Comment createComment(@PathVariable String id) {
        Comment comment = repo.findById(id).orElse(new Comment());

        return comment;
    }

    @RequestMapping("/demo/getUser")
    public Map<String, Object> getUser() {
        return this.userMap;
    }

}
