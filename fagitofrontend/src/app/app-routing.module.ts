import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { NgModule, Injectable } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchComponent } from './search/search.component';


const routes: Routes = [
  {
    path : 'Login',component:LoginComponent
  },
  {
    path :'Signup',component:SignupComponent
  },
  {
    path : 'Search',component:SearchComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
    routes

  )],
  exports: [RouterModule]
})
@Injectable()
export class AppRoutingModule { }
