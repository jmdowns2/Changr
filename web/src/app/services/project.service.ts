import { Injectable } from '@angular/core';
//import { Response } from '@angular/http';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project, CreateProject } from '../schema/project'
import { Job } from '../schema/job'
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private apiBase:string;
  private baselineBase:string;

  constructor(private http:HttpClient) { 
    this.apiBase = environment.apiBase;
    this.baselineBase = environment.baselineBase;
  }

  public getProject(id:string) : Observable<Project> {
    return this.http.get<Project>(`${this.apiBase}/projects/${id}`);
  }

  public updateProject(project:Project) : Observable<any> {
    return this.http.put(`${this.apiBase}/projects/${project.id}`, project);
  }

  public createProject(project:CreateProject) : Observable<any> {
    return this.http.post(`${this.apiBase}/projects/`, project);
  }

  public getProjects() : Observable<Array<Project>> {
    return this.http.get<Array<Project>>(`${this.apiBase}/projects/`);
  }

  public getJob(jobId:string) : Observable<Job> {
    return this.http.get<Job>(`${this.apiBase}/jobs/${jobId}/`);
  }


  public getJobs(projectId:string) : Observable<Array<Job>> {
    return this.http.get<Array<Job>>(`${this.apiBase}/jobs/?projectId=${projectId}`);
  }


  public runProject(p:Project) : Observable<any> {
    var payload = { projectId: p.id };
    return this.http.post(`${this.apiBase}/jobs/`, payload);
  }

  public setAsBaseline(job:Job) : Observable<any> {
    var payload = { newBaselineJobId: job.id };
    return this.http.post(`${this.baselineBase}/files/project/${job.projectId}/`, payload);
  }
  
  public getBaseline(job:Job) : Observable<any> {
    return this.http.get(`${this.baselineBase}/files/project/${job.projectId}/`, {responseType: 'text'});
  }

  public getJobOutput(job:Job) : Observable<any> {
    return this.http.get(`${this.baselineBase}/files/job/${job.id}/`, {responseType: 'text'});
  }

}
