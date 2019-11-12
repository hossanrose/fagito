package com.fagito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fagito.service.FoodSearchService;
import com.fagito.service.FoodSearchServiceInterface;
import com.fagito.view.Food_Form;
import com.fagito.view.Food_Form_UI;

@RestController
@RequestMapping("/api/food_search")
public class Search_Food_Controller {
	
	@Autowired
	private FoodSearchServiceInterface foodSearchServiceInterface;
	
	@GetMapping
	public List<Food_Form> food_search(@RequestBody Food_Form_UI food_form_ui)
	{
		List<Food_Form> food_form=foodSearchServiceInterface.foodSearch(food_form_ui);
		
		return food_form;
	}
	
	

}
