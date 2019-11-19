import { LoginComponent } from './../login/login.component';
import { Food_Search_Result } from './../componentmodels/food_search_results';
import { Router } from '@angular/router';
import { SearchService } from './search.service';
import { Food_Search } from './../componentmodels/food_search_location';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router'
import{Food_Details} from './../componentmodels/food_details';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  food_details:Food_Details;
  food_search:Food_Search;
  route: any;
  id:string;
  sub:any;
  constructor(private searchservice:SearchService,private _router:Router) { 
    this.food_search=new Food_Search();
    this.food_details=new Food_Details();
  }
  
  ngOnInit() {

    //this.sub = this.route.params.subscribe(params => {
     // this.id = params['id'];
      //});
      //console.log(this.id);

    if(navigator.geolocation)
    {
      navigator.geolocation.getCurrentPosition((position) => {
        this.food_search.latitude = position.coords.latitude;
        this.food_search.longitude=position.coords.longitude;
        console.log(this.food_search);
        this.searchservice.getWeather(position.coords);
      });
    }
    
    
  }
  

  food_id:string;
  customer_id:string;
  rest_id:string;
  
   

  food_name:string;
  food_result:any;
  food_search_result:Food_Search_Result;
  //search based on location and search filter : food_name
  find()
  {
   this.food_search.food_name=this.food_name;
   console.log(this.food_search);
   this.searchservice.save(this.food_search).subscribe(
    result=>{
      this.food_search_result=JSON.parse(result.text());
      console.log(this.food_search_result);
          }
      );
  }
  
 find_details(food_id:string,restaurant_id:string)
 {
   this.food_details.food_id=food_id;
   this.food_details.customer_id=this.customer_id;
   this.food_details.restaurant_id=this.rest_id;
   this.searchservice.get_food_price(this.food_details).subscribe(
     result=>{

     }
   )
 }

 //get food_details based on food_id
}
