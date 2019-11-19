
import { SignupComponent } from './signup.component';
import { User } from './../componentmodels/user';
import { Injectable } from '@angular/core';
import { Http, RequestOptions, RequestOptionsArgs, Headers } from '@angular/http';
import { JsonPipe } from '@angular/common';
import { Router } from '@angular/router';
import{ map } from 'rxjs/operators';
import{URL} from './../componentmodels/common';

//import { ContentType } from '@angular/http/src/enums';
//import { HttpClient } from 'selenium-webdriver/http';
 
@Injectable()
export class SignUpService{
 
  private usersUrl: URL;
  private header = new Headers({'content-type': 'application/json'});
  private options = new RequestOptions({headers : this.header});
  private signupComponent:SignupComponent;
  private _router:Router;
  constructor(private http: Http) {
    this.usersUrl=new URL();
  }

  
//UserController
  public save(user: User){
    return this.http.post(this.usersUrl+"users/signup",user,this.options)
    .pipe(map(response=>response));

  }

}