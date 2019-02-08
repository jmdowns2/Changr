import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
//import { HttpModule } from '@angular/http';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { LoginComponent } from './login/login.component';
import { ProjectsComponent } from './projects/projects.component';
import { ProjectService } from './services/project.service';
import { AuthService } from './services/auth.service';

import { UnathorizedInterceptor } from './services/unauthorized.interceptor'
import { JWTInterceptor } from './services/jwt.interceptor'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProjectsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JWTInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: UnathorizedInterceptor, multi: true },
    ProjectService, AuthService, 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
