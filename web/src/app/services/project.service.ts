import { Injectable } from '@angular/core';
//import { Response } from '@angular/http';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../schema/project'
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
    return this.http.get<Project>(`${this.apiBase}/${id}`);
  }

  public updateProject(project:Project) : Observable<any> {
    return this.http.put(`${this.apiBase}/${project.id}`, project);
  }

  public getProjects() : Observable<Array<Project>> {
    return this.http.get<Array<Project>>(`${this.apiBase}/`);
  }

  public runProject(p:Project) : Observable<any> {
    return this.http.post(`${this.apiBase}/${p.id}/run`, {});
  }

}
