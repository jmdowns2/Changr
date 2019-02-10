import { Injectable } from '@angular/core';
//import { Response } from '@angular/http';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../schema/project'
import { Job } from '../schema/job'
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private apiBase:string;
  constructor(private http:HttpClient) { 
    this.apiBase = environment.apiBase;
  }

  public getProject(id:string) : Observable<Project> {
    return this.http.get<Project>(`${this.apiBase}/projects/${id}`);
  }

  public updateProject(project:Project) : Observable<any> {
    return this.http.put(`${this.apiBase}/projects/${project.id}`, project);
  }

  public getProjects() : Observable<Array<Project>> {
    return this.http.get<Array<Project>>(`${this.apiBase}/projects/`);
  }

  public getJob(jobId:string) : Observable<Job> {
    return this.http.get<Job>(`${this.apiBase}/jobs/${jobId}/`);
  }


  public getJobs(projectId:string) : Observable<Array<Job>> {
    return this.http.get<Array<Job>>(`${this.apiBase}/jobs/?project=${projectId}`);
  }


  public runProject(p:Project) : Observable<any> {
    var payload = { projectId: p.id };
    return this.http.post(`${this.apiBase}/jobs/`, payload);
  }

}
