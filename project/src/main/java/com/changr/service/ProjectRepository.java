package com.changr.service;



import com.changr.model.Project;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface ProjectRepository extends
        CrudRepository<Project, String> {

    List<Project> findByUser(String user);
    Optional<Project> findById(String id);

    Iterable<Project>	findAll();
}