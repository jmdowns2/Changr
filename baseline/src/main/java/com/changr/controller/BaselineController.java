package com.changr.controller;

import com.amazonaws.util.IOUtils;
import com.changr.exceptions.BaselineNotFoundException;
import com.changr.model.FileResponse;
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
import java.net.URL;

@RestController
@EnableWebMvc
@RequestMapping(path="/files/")
public class BaselineController {

    @Autowired
    FileService files;

    @GetMapping(path="/job/{jobId}")
    FileResponse getJobOutput(@PathVariable String jobId, Authentication auth, HttpServletResponse response) throws IOException {
        URL url = files.getOutput(jobId);

        return new FileResponse(url.toExternalForm());
    }

    @GetMapping(path="/job/{jobId}/diff")
    FileResponse getJobOutputDiff(@PathVariable String jobId, Authentication auth, HttpServletResponse response) throws IOException {
        URL url =  files.getOutputDiff(jobId);

        return new FileResponse(url.toExternalForm());
    }




    @GetMapping(path="/project/{projectId}")
    FileResponse getBaselineOutput(@PathVariable String projectId, Authentication auth, HttpServletResponse response) throws IOException {
        URL url = files.getBaseline(projectId);

        if(url == null)
        {
            throw new BaselineNotFoundException();
        }

        return new FileResponse(url.toExternalForm());
    }

    @PostMapping(path="/project/{projectId}")
    void makeBaseline(@RequestBody MakeBaseline body, @PathVariable String projectId, Authentication auth)  {

        files.makeBaseline(body.newBaselineJobId);
    }

}
