package com.changr.repository;


import com.changr.model.Job;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface JobRepository extends
        CrudRepository<Job, String> {

    Optional<Job> findById(String id);


}
