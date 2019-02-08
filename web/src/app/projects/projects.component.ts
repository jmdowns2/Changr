import { Component, OnInit } from '@angular/core';
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

    this.projectService.getProjects().subscribe((res) => {
      this.projects=res;
    });
  }

}
