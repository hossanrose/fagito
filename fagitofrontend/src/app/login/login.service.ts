import { UserLogin } from './../componentmodels/userlogin';
import { LoginComponent } from './login.component';
import { Injectable } from "@angular/core";
import { Http, RequestOptions, RequestOptionsArgs, Headers } from '@angular/http';
import { Router } from '@angular/router';
import{ map } from 'rxjs/operators';
import{URL} from './../componentmodels/common';


 
@Injectable()
export class LoginService{
 
  private usersUrl: URL;
  private header = new Headers({'content-type': 'application/json'});
  private options = new RequestOptions({headers : this.header});
  private loginComponent:LoginComponent;

  constructor(private http: Http) {
    this.usersUrl=new URL();

  }

  
//UserController
  public sent(userlogin:UserLogin){
    console.log(this.usersUrl.usersUrl);
    return this.http.post(this.usersUrl.usersUrl+'/users/login',userlogin,this.options)
    .pipe(map(response=>response));

  }

}