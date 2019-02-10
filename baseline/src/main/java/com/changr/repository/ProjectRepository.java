package com.changr.repository;

import com.changr.model.Job;
import com.changr.model.Project;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface ProjectRepository extends
        CrudRepository<Project, String> {

    Optional<Project> findById(String id);


}