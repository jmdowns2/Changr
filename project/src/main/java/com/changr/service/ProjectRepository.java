package com.changr.service;



import com.changr.model.Project;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ProjectRepository extends
        CrudRepository<Project, String> {

    List<Project> findById(Id id);

    Iterable<Project>	findAll();
}