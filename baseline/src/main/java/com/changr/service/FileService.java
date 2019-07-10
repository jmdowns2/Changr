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
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;


@Service
public class FileService {

    final String BASELINE_FILE = "out.png";


    @Value(value = "${bucket}")
    String bucket;

    @Autowired
    JobRepository jobsRepo;

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    AmazonS3 s3;

    private Date getPresignedUrlExpiration()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 6);
        return cal.getTime();
    }

    public URL getOutput(String jobId)
    {
        Job job = jobsRepo.findById(jobId).orElseThrow(() -> new BaselineNotFoundException());
        String key = job.getUser()+"/"+job.getProjectId()+"/jobs/"+job.getId()+"/out.png";
        return s3.generatePresignedUrl(bucket, key, getPresignedUrlExpiration());
    }

    public URL getOutputDiff(String jobId)
    {
        Job job = jobsRepo.findById(jobId).orElseThrow(() -> new BaselineNotFoundException());
        String key = job.getUser()+"/"+job.getProjectId()+"/jobs/"+job.getId()+"/out-diff.png";
        return s3.generatePresignedUrl(bucket, key, getPresignedUrlExpiration());
    }

    public void makeBaseline(String jobId)
    {
        Job job = jobsRepo.findById(jobId).orElseThrow(() -> new BaselineNotFoundException());
        String outputKey = job.getUser()+"/"+job.getProjectId()+"/jobs/"+job.getId()+"/out.png";

        Project p = projectRepo.findById(job.getProjectId()).orElseThrow(() -> new BaselineNotFoundException());
        String baselineKey = buildBaselineKey(p);

        s3.copyObject(bucket, outputKey, bucket, baselineKey);
    }

    public URL getBaseline(String projectId)
    {
        Project p = projectRepo.findById(projectId).orElseThrow( () -> new BaselineNotFoundException() );
        String key = buildBaselineKey(p);


        if(!s3.doesObjectExist(bucket, key))
            return null;

        return s3.generatePresignedUrl(bucket, key, getPresignedUrlExpiration());
    }

    protected String buildBaselineKey(Project p)
    {
        return p.getUser()+"/"+p.getId()+"/baseline/"+BASELINE_FILE;
    }

}
