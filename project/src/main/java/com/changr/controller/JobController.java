package com.changr.controller;

import com.changr.exceptions.ProjectNotFoundException;
import com.changr.model.Job;
import com.changr.model.JobCreation;
import com.changr.model.Project;
import com.changr.model.ProjectConfig;
import com.changr.service.JobRepository;
import com.changr.service.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.ws.rs.Path;
import java.util.List;
import java.util.Optional;

@RestController
@EnableWebMvc
@RequestMapping(path = "/jobs")
public class JobController {

    @Autowired
    private JobRepository jobsRepo;

    @Autowired
    private ProjectRepository projectRepo;

    @PostMapping(path = "/")
    public void create(@RequestBody JobCreation body, Authentication authentication) {

        Project p = projectRepo.findById(body.projectId)
                .orElseThrow(() -> new ProjectNotFoundException());

        if(p.getUser().compareTo(authentication.getName()) != 0)
            throw new ProjectNotFoundException();

        Job j = Job.createFromProject(p);
        jobsRepo.save(j);
    }

    @GetMapping(path = "/")
    public List<Job> listForProject(@RequestParam(required = false) String projectId, Authentication authentication) {

        if(projectId == null)
            return jobsRepo.findByUserId(authentication.getName());

        Project p = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException());

        if(p.getUser().compareTo(authentication.getName()) != 0)
            throw new ProjectNotFoundException();

        return jobsRepo.findByUserIdAndProjectId(authentication.getName(), p.getId());
    }

    @GetMapping(path = "/{jobId}/")
    public Job getJob(@PathVariable String jobId, Authentication authentication) {

        Job j = jobsRepo.findById(jobId)
                .orElseThrow(() -> new ProjectNotFoundException());

        if(j.getUserId().compareTo(authentication.getName()) != 0)
            throw new ProjectNotFoundException();

        return j;
    }

}
