import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  setToken(t:string) {
    window.localStorage.setItem("id_token", t);
  }
  getToken() : string {
    return window.localStorage.getItem("id_token");
  }

  login() {
    var callback = window.location.origin;
    var url = `${environment.cognitoLoginUrl}/login?response_type=token&client_id=${environment.cognitoAppId}&redirect_uri=${callback}`;
    window.location.href = url;

  }
}
