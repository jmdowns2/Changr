import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../services/project.service';
import { Job } from '../schema/job';
import {DomSanitizer, SafeUrl} from '@angular/platform-browser';


@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})
export class JobComponent implements OnInit {

  id:string;
  job:Job;

  baselineUrl:SafeUrl = "";
  outputUrl:SafeUrl = "";
  diffUrl:SafeUrl = "";

  showDiff:boolean = true;
  showOriginal:boolean = false;
  showCurrent:boolean = true;

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
      this.baselineUrl = baseline.url;
    })
    this.projectService.getJobOutput(this.job).subscribe((out) => { 
      this.outputUrl = out.url;
    })
    this.projectService.getJobOutputDiff(this.job).subscribe((out) => { 
      this.diffUrl = out.url;
    })

    
  }
  setAsBaseline() {
    this.projectService.setAsBaseline(this.job).subscribe(() => {

    });
  }

  
}
