/*package com.fagito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fagito.view.Restaurant_Form_UI;

@RestController
@RequestMapping("/api/restaurant")
public class Restaurant_Controller {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping
	public ResponseEntity<?> add_restaurant(@RequestBody Restaurant_Form_UI restaurant_form_ui)
	{
		try
		{
			return ResponseEntity.ok().body(restaurantService.addMenu(restaurant_form_ui));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}
}
*/