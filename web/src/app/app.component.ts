import { Component } from '@angular/core';
import { AuthService } from './services/auth.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private auth:AuthService) {
    this.parseIdToken(window.location.hash);
  }
  
  private parseIdToken(hash:String)
  {
    if(hash[0] == "#") hash = hash.substr(1);
    var vals = hash.split("&").map( val => val.split("="))
    var token = vals.filter(val => val[0] == "id_token");
    if(token.length == 0) return;
    var val = token[0][1];
    if(val.length > 0)
      this.auth.setToken(val);
  }



  onLogin() {
    this.auth.login();
  }
}
