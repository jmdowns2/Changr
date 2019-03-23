import { Component } from '@angular/core';
import { AuthService } from './services/auth.service'
import { Router, NavigationStart } from '@angular/router';
import { filter } from 'rxjs/operators';
import { environment } from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private auth:AuthService, private router:Router) {
    this.parseIdToken(window.location.hash);

    this.router.events.pipe(
      filter(event => event instanceof NavigationStart))
      .subscribe((event:NavigationStart) => {
        //event.url
        (<any>window).gtag('config', environment.gaMeasurementId, {'page_path': event.url });
      });
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


  onNav(route:string)
  {
    this.router.navigate([route]);
  }

  onLogin() {
    this.auth.login();
  }
}
