import { Injectable } from '@angular/core';

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
    var appId = "";
    var callback = encodeURIComponent("http://localhost:4200/")
    var url = `https://testing1.auth.us-east-1.amazoncognito.com/login?response_type=token&client_id=${appId}&redirect_uri=${callback}`;
    window.location.href = url;
  }
}
