package com.changr.controller;

import com.amazonaws.util.IOUtils;
import com.changr.exceptions.BaselineNotFoundException;
import com.changr.model.MakeBaseline;
import com.changr.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@EnableWebMvc
@RequestMapping(path="/files/")
public class BaselineController {

    @Autowired
    FileService files;

    @GetMapping(path="/job/{jobId}/")
    void getJobOutput(@PathVariable String jobId, Authentication auth, HttpServletResponse response) throws IOException {
        InputStream in = files.getOutput(jobId);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping(path="/job/{jobId}/diff")
    void getJobOutputDiff(@PathVariable String jobId, Authentication auth, HttpServletResponse response) throws IOException {
        InputStream in = files.getOutputDiff(jobId);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }



    @GetMapping(path="/project/{projectId}/")
    void getBaselineOutput(@PathVariable String projectId, Authentication auth, HttpServletResponse response) throws IOException {
        InputStream in = files.getBaseline(projectId);

        if(in == null)
        {
            throw new BaselineNotFoundException();
        }

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @PostMapping(path="/project/{projectId}/")
    void makeBaseline(@RequestBody MakeBaseline body, @PathVariable String projectId, Authentication auth)  {

        files.makeBaseline(body.newBaselineJobId);
    }

}
