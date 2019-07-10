package com.changr.controller;

import com.changr.exceptions.ProjectNotFoundException;
import com.changr.model.Project;
import com.changr.model.ProjectConfig;
import com.changr.service.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.*;

@RestController
@EnableWebMvc
@RequestMapping(path = "/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository repo;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public void create(@RequestBody Project body, Authentication authentication) {

        Project p = Project.create(authentication.getName(), body.getName());

        ProjectConfig config = new ProjectConfig();
        p.setConfig(config);
        repo.save(p);

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable String id, Authentication auth) {
        Optional<Project> opt = repo.findById(id);

        if(!opt.isPresent())
            throw new ProjectNotFoundException();

        Project ret = opt.get();
        if(ret.getUser().compareTo(auth.getName()) != 0)
            throw new ProjectNotFoundException();

        return ret;
    }

    @PutMapping(path = "/{id}")
    public void updateProject(@RequestBody Project p, Authentication auth) {
        p.setUser(auth.getName());
        repo.save(p);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Project> getProjects() {

        Iterable<Project> it = repo.findAll();
        List<Project> ret = new ArrayList<Project>();
        it.forEach(ret::add);
        return ret;

    }
/*
    @PostMapping(path = "/{id}/run")
    public String runProject(@PathVariable String id, Authentication auth) {

        Optional<Project> opt = repo.findById(id);
        if(!opt.isPresent())
            throw new ProjectNotFoundException();

        Project p = opt.get();
        if(p.getUser().compareTo(auth.getName()) != 0)
            throw new ProjectNotFoundException();

        Job fulfill = new Job(p.getId(), auth.getName());
        executeProducer.send(fulfill);
        return "";
    }
    */

}
