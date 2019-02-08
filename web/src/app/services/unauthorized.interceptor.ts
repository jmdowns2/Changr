import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { HttpInterceptor, HttpRequest, HttpEvent, HttpHandler, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap  } from 'rxjs/operators';
import { AuthService } from "./auth.service"
//import 'rxjs/add/operator/do';

@Injectable()
export class UnathorizedInterceptor implements HttpInterceptor {
    constructor(private auth:AuthService) {}
    
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {      
      return next.handle(request).pipe(
        tap(
          (res) => {res.type},
          (err) => { 
            if (err instanceof HttpErrorResponse) {
              if (err.status === 401) {
                return this.handle401(err);
              }
            }            
          }
        ));
    }

    handle401(error) {
      this.auth.login();
    }
}