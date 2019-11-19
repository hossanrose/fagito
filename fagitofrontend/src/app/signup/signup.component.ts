import { LoginComponent } from './../login/login.component';
import { Component, Injectable} from '@angular/core';
import { User } from '../componentmodels/user';
import { SignUpService } from './signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  users:User;

  constructor(private signupservice:SignUpService){
    this.users=new User();
  }
  customer_name : string;
  password : string;
  email :string;
  comment:string;
  onClick()
  {
    this.users.customer_name=this.customer_name;
    this.users.password=this.password;
    this.users.email=this.email;
    console.log(this.users);
    this.signupservice.save(this.users).subscribe(
      result=>this.comment=result.text()
      );
  }
  
}


