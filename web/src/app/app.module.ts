import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ProjectsModule } from './projects/projects.module';

import { LoginComponent } from './login/login.component';

import { ProjectService } from './services/project.service';
import { AuthService } from './services/auth.service';

import { UnathorizedInterceptor } from './services/unauthorized.interceptor'
import { JWTInterceptor } from './services/jwt.interceptor'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ProjectsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JWTInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: UnathorizedInterceptor, multi: true },
    ProjectService, AuthService, 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
