package kg.attractor.votemachine.controller;

import kg.attractor.votemachine.model.Candidate;
import kg.attractor.votemachine.model.CandidateRepository;
import kg.attractor.votemachine.service.CandidateService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CandidateController {
    private final CandidateService service;
    private final CandidateRepository repository;

    public CandidateController(CandidateRepository repository, CandidateService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public String root(Model model) {
        model.addAttribute("candidates", repository.findAll());
        return "candidates";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/candidate")
    public String rootGet(Model model) {
        return "addcandidate";
    }

    @PostMapping("/candidate")
    public String rootSave(@RequestParam("name") String name,
                           @RequestParam("photo") MultipartFile photo) throws IOException {
        String path = "../images";
        File photoFile = new File(path + "/" + photo.getOriginalFilename());
        FileOutputStream os = new FileOutputStream(photoFile);
        os.write(photo.getBytes());
        os.close();

        Candidate candidate = new Candidate(name, photo.getOriginalFilename());
        repository.save(candidate);

        return "success";
    }

    @GetMapping("/votes")
    public String handleVotes(Model model) {
        model.addAttribute("candidates", service.candidatesWithVotes());
        return "votes";
    }

    @GetMapping("/thankyou/{candidateId}")
    public String handleThankYou(@PathVariable String candidateId, Model model) {
        var candidate = service.getById(candidateId);
        model.addAttribute("person", candidate);
        model.addAttribute("votes", service.calculatePercentForOne(candidate));

        return "thankyou";
    }

    @PostMapping("/vote")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String handleRegisterVote(@RequestParam(defaultValue = "--no-value--") String candidateId) {
        service.voteFor(candidateId);
        return "redirect:/thankyou/" + candidateId;
    }

    @GetMapping("/demo")
    public String demo(Model model) {
        return "demo";
    }

    @PostMapping("/demo/post")
    public String postDemo(@RequestParam("login") String login, @RequestParam("password") String password) {
        System.out.println(login);
        System.out.println(password);
        return "redirect:/demo/";
    }

    @GetMapping("/image/{name}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) {
        String path = "../images";
        try {
            InputStream is = new FileInputStream(new File(path) + "/" + name);
            return ResponseEntity
                    .ok()
                    .contentType(name.toLowerCase().contains(".png")?MediaType.IMAGE_PNG:MediaType.IMAGE_JPEG)
                    .body(StreamUtils.copyToByteArray(is));
        } catch (Exception e) {
            InputStream is = null;
            try {
                is = new FileInputStream(new File(path) + "/" + name);
                return ResponseEntity
                        .ok()
                        .contentType(name.toLowerCase().contains(".png")?MediaType.IMAGE_PNG:MediaType.IMAGE_JPEG)
                        .body(StreamUtils.copyToByteArray(is));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/image2/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage2(@PathVariable("name") String name) {
        String path = "../images";
        try {
            InputStream is = new FileInputStream(new File(path) + "/" + name);
            return StreamUtils.copyToByteArray(is);
        } catch (Exception e) {
            InputStream is = null;
            try {
                is = new FileInputStream(new File(path) + "/" + name);
                return StreamUtils.copyToByteArray(is);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/candidates")
    @ResponseBody
    public Iterable<Candidate> getCondidateList() {
        return repository.findAll();
    }

}
