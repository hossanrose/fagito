import { AppRoutingModule } from './../app-routing.module';
import { UserLogin } from './../componentmodels/userlogin';
//import { Location } from './../componentmodels/Location';
import { Component, Input, Output} from '@angular/core';
import { LoginService } from './login.service';
import { Router} from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

  userlogin:UserLogin;
  
  constructor(private loginService:LoginService,private _router:Router)
  {
      this.userlogin=new UserLogin();
  }
  email:string;
  password:string;
  comment:any;
  onClick()
  {
    this.userlogin.email=this.email;
    this.userlogin.password=this.password;
    console.log(this.userlogin);
    this.loginService.sent(this.userlogin).subscribe(
      result=>{
             this.navigate(result.text());
            },
      error=>{this.comment=error.text()}
      );
  }
  navigate(result:string)
  {
    if(result.substr(0,1)=="S" || result.substr(0,1)=="C")
    {
      console.log(result)
      window.location.replace;
      this._router.navigate(['Search']);
    }
  }
  


 /*locate:Location;
  constructor(private loginservice:LoginService) { 
    this.locate=new Location();
  }
  ngOnInit() {
    if(navigator.geolocation)
    {
      navigator.geolocation.getCurrentPosition((position) => {
        this.locate.latitude = position.coords.latitude;
        this.locate.longitude=position.coords.longitude;
      });
    }
    this.loginservice.give(this.locate);
  }*/
  
}