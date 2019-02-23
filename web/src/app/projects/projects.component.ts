import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { CreateProject } from "../schema/project"
import { ProjectService } from '../services/project.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  projects:Array<any> = [];

  constructor(private projectService:ProjectService) { }

  ngOnInit() {
    this.fetchProjects();
  }

  fetchProjects() {
    this.projectService.getProjects().subscribe((res) => {
      this.projects=res;
    });
  }

  createProject() {
    var project:CreateProject = { "name": "New Project" };
    this.projectService.createProject(project).subscribe((res) => {
      this.fetchProjects();
    })
  }
}
