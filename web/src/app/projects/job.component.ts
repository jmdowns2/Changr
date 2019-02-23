import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../services/project.service';
import { Job } from '../schema/job';


@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})
export class JobComponent implements OnInit {

  id:string;
  job:Job;

  baseline:string;
  output:string;

  constructor(private route:ActivatedRoute, private projectService:ProjectService) { }

  ngOnInit() {

    this.route.params.subscribe((params) => {
      this.id = params["jobId"];

      this.projectService.getJob(this.id).subscribe((j) => { 
        this.job = j;
        this.fetchOutput();
      })

      

    });

  }

  fetchOutput() { 
    this.projectService.getBaseline(this.job).subscribe((baseline) => { 
      this.baseline = baseline;
    })
    this.projectService.getJobOutput(this.job).subscribe((out) => { 
      this.output = out;
    })
  }
  setAsBaseline() {
    this.projectService.setAsBaseline(this.job).subscribe(() => {

    });
  }
}
