import { RouterTestingModule } from '@angular/router/testing';
import { RouterModule, Router } from '@angular/router';
import { Http, HttpModule } from '@angular/http';
import { SignUpService } from './signup/signup.service';
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import {BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from './login/login.service';
import { SearchComponent } from './search/search.component';
import { SearchService } from './search/search.service';


@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    HttpModule,
    HttpClientJsonpModule,
    BrowserAnimationsModule,
    RouterModule,
    RouterTestingModule
  ],
  providers: [SignUpService,LoginService,SearchService],
  bootstrap: [AppComponent]
})
export class AppModule { }
