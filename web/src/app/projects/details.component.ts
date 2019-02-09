import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../services/project.service';
import { Project } from "../schema/project";

@Component({
  selector: 'project-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  id:string = "";
  project:Project = null;

  constructor(private route:ActivatedRoute, private projectService:ProjectService) { }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.id = params["id"];

      this.projectService.getProject(this.id).subscribe((p) => { this.project = p;})

    });
  }

  save() {
    if(!this.project) return;
    this.projectService.updateProject(this.project).subscribe(() => {});
  }

  executeRun() {
    this.projectService.runProject(this.project).subscribe(() => {});
  }

}
