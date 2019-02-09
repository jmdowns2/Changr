package com.changr.service;


import com.changr.model.Job;
import com.changr.model.Project;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface JobRepository extends
        CrudRepository<Job, String> {

    List<Job> findByUserId(String user);
    List<Job> findByUserIdAndProjectId(String user, String projectId);
    Optional<Job> findById(String id);


}
