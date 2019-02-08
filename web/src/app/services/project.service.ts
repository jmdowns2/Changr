import { Injectable } from '@angular/core';
//import { Response } from '@angular/http';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http:HttpClient) { }

  public getProjects() : Observable<any> {
    return this.http.get("http://127.0.0.1:8080/")
  }

}
