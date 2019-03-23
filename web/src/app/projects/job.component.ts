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

  constructor(private route:ActivatedRoute, private projectService:ProjectService, private sanitizer:DomSanitizer) { }

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
      this.baselineUrl = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(baseline));
    })
    this.projectService.getJobOutput(this.job).subscribe((out) => { 
      this.outputUrl = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(out));
    })
    this.projectService.getJobOutputDiff(this.job).subscribe((out) => { 
      this.diffUrl = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(out));
    })

    
  }
  setAsBaseline() {
    this.projectService.setAsBaseline(this.job).subscribe(() => {

    });
  }

  
}
