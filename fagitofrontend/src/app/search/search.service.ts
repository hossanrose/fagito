import { HttpClientJsonpModule ,HttpClientModule} from '@angular/common/http';
import { Food_Details } from './../componentmodels/food_details';
import { Food_Search } from './../componentmodels/food_search_location';
import { SearchComponent } from './search.component';
import { Injectable } from '@angular/core';
import { Http, RequestOptions, RequestOptionsArgs, Headers } from '@angular/http';
import { JsonPipe } from '@angular/common';
import { Router } from '@angular/router';
import{ map } from 'rxjs/operators';
import{URL} from './../componentmodels/common';


//import { ContentType } from '@angular/http/src/enums';
//import { HttpClient } from 'selenium-webdriver/http';
 
@Injectable()
export class SearchService{
 
  private weather:any;
  private appId:string;
  private appCode:string;
  private usersUrl: URL;
  private header = new Headers({'content-type': 'application/json'});
  private options = new RequestOptions({headers : this.header});
  private _router:Router;
  constructor(private http: Http) {
    this.usersUrl = new URL();
    this.appId = "APP-ID";
    this.appCode = "APP-CODE";
    this.weather=[];
  }
  public getWeather(coordinates: any) {
    this.http.get("https://weather.cit.api.here.com/weather/1.0/report.json?product=forecast_7days_simple&latitude=" + coordinates.latitude + "&longitude=" + coordinates.longitude + "&app_id=" + this.appId + "&app_code=" + this.appCode, "jsonpCallback")
        .pipe(map(result => (<any>result).dailyForecasts.forecastLocation))
        .subscribe(result => {
            this.weather = result.forecast;
            console.log(this.weather);
        }, error => {
            console.error(error);
        });
}

  
  //search based on location and search filter : food_name --Search_Food_Controller
  public save(food_search:Food_Search){
    return this.http.post(this.usersUrl+"search/food_search",food_search,this.options)
    .pipe(map(response=>response));
  }
  //get food details based on food_id, rest_id and cust_id
  public get_food_price(food_details:Food_Details)
  {
    return this.http.post(this.usersUrl+"food_price/specific_food",food_details,this.options)
    .pipe(map(response=>response));
  }
  //get food details based on customer_id
}