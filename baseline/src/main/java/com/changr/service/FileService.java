package com.changr.service;

import com.amazonaws.services.s3.AmazonS3;
import com.changr.exceptions.BaselineNotFoundException;
import com.changr.model.Job;
import com.changr.model.Project;
import com.changr.repository.JobRepository;
import com.changr.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.stream.Stream;


@Service
public class FileService {

    final String BASELINE_FILE = "out.html";


    @Value(value = "${bucket}")
    String bucket;

    @Autowired
    JobRepository jobsRepo;

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    AmazonS3 s3;

    public InputStream getOutput(String jobId)
    {
        Job job = jobsRepo.findById(jobId).orElseThrow(() -> new BaselineNotFoundException());
        String key = job.getOutput();

        return s3.getObject(bucket, key).getObjectContent();
    }

    public void makeBaseline(String jobId)
    {
        Job job = jobsRepo.findById(jobId).orElseThrow(() -> new BaselineNotFoundException());
        String outputKey = job.getOutput();

        Project p = projectRepo.findById(job.getProjectId()).orElseThrow(() -> new BaselineNotFoundException());
        String baselineKey = buildBaselineKey(p);

        s3.copyObject(bucket, outputKey, bucket, baselineKey);
    }

    public InputStream getBaseline(String projectId)
    {
        Project p = projectRepo.findById(projectId).orElseThrow( () -> new BaselineNotFoundException() );
        String key = buildBaselineKey(p);

        if(!s3.doesObjectExist(bucket, key))
            return null;

        return s3.getObject(bucket, key).getObjectContent();
    }

    protected String buildBaselineKey(Project p)
    {
        return p.getUser()+"/"+p.getId()+"/baseline/"+BASELINE_FILE;
    }

}
