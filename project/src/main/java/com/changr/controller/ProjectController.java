package com.changr.controller;

import com.changr.model.Project;
import com.changr.service.ProjectRepository;
import com.changr.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

@RestController
@EnableWebMvc
public class ProjectController {


    @Autowired
    private ProjectRepository repo;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String create() {

        Project p = Project.create("myid","TEST", "abc");
        repo.save(p);
        return "OK";

    }

    @RequestMapping(path = "/projects", method = RequestMethod.GET)
    public Map<String, String> getProjects() {

        Iterable<Project> it = repo.findAll();

        Map<String, String> pong = new HashMap<>();
        it.forEach((p -> pong.put("pong", p.getUser().toString()) ));
        return pong;

    }

}
