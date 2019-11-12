package com.fagito.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.model.Food;
import com.fagito.model.Menu;
import com.fagito.model.Restaurant;
import com.fagito.model.RestaurantRecord;
import com.fagito.repository.FoodRepository;
import com.fagito.repository.MenuRepository;
import com.fagito.repository.RestaurantRecordRepository;
import com.fagito.repository.RestaurantRepository;
import com.fagito.view.Food_Form;
import com.fagito.view.Food_Form_UI;

@Service
public class FoodSearchService implements FoodSearchServiceInterface{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired 
	private FoodRepository foodRepository;
	@Autowired
	private RestaurantRecordRepository restaurantrecordRepository;
	
	Map<String,Float>distance_map=new HashMap<String,Float>();
	
	@SuppressWarnings("deprecation")
	public List<Food_Form> foodSearch(Food_Form_UI food_form_ui)
	{
		int distance=10;
		boolean is_gold;
		String restaurant_id,menu_id;
		List<Food_Form> food_form=new ArrayList<Food_Form>();
		List<Menu> menu_list=null;
		List<Restaurant> restaurant_list=null;
		
		Iterator<Food> food_iterator;
		Iterator<Menu> menu_iterator;
		Iterator<Restaurant> restaurant_iterator;
		
		
		System.out.println(food_form_ui.getFood_name().toLowerCase());
		List<Food> food_list=foodRepository.findByName(food_form_ui.getFood_name().toLowerCase());

		if(food_list!=null)
		{
			menu_list=this.getMenuList(food_list);
			restaurant_list=this.getRestaurantList(menu_list);
		}
		else
		{
			food_list=foodRepository.findAll();
			menu_list=this.getMenuList(food_list);
			restaurant_list=this.getRestaurantList(menu_list);
		}
		System.out.println(food_list);
		System.out.println(menu_list);
		System.out.println(restaurant_list);
		restaurant_list=get_restaurant_with_distance(restaurant_list,food_form_ui.getLatitude(),food_form_ui.getLongitude(),distance);
		
		food_iterator=food_list.iterator();
		menu_iterator=menu_list.iterator();
		restaurant_iterator=restaurant_list.iterator();
		
		while(food_iterator.hasNext())
		{
			Food_Form food_form_object=new Food_Form();
			Food food_object=food_iterator.next();
			
			menu_id=(String)food_object.getMenu_id();
			
			while(menu_iterator.hasNext())
			{
				Menu menu_object=menu_iterator.next();
				if(menu_object.getMenu_id().equals(menu_id))
				{
					restaurant_id=menu_object.getRestaurant_id();
					
					while(restaurant_iterator.hasNext())
					{
						Timestamp ts=new Timestamp(System.currentTimeMillis());
						Restaurant restaurant_object=restaurant_iterator.next();
						if(restaurant_object.getRestaurant_id().equals(restaurant_id))
						{
							food_form_object.setResteraunt_name(restaurant_object.getRestaurant_name());;
							food_form_object.setFood_name(food_object.getFood_name());
							food_form_object.setTime(restaurant_object.getOpening_time()+"-"+restaurant_object.getClosing_time());
							//System.out.print(restaurantrecordRepository.findIsGold(restaurant_object.getRestaurant_id()));
							is_gold=restaurantrecordRepository.getIsGold(restaurant_object.getRestaurant_id());
							if(is_gold)
								food_form_object.setIs_gold(1);
							else
								food_form_object.setIs_gold(0);
							
							if(ts.getHours()>=restaurant_object.getOpening_time().getHours() && ts.getHours()<=restaurant_object.getClosing_time().getHours())
							{
								food_form_object.setOpenorclose("Open");
							}
							else
							{
								food_form_object.setOpenorclose("Close");
							}
							
							food_form_object.setDistance(distance_map.get(restaurant_object.getRestaurant_id()));
							food_form.add(food_form_object);
						}
					}
				}
			}	
		}
		return food_form;
		
		
	}
	
	public List<Menu> getMenuList(List<Food> food_list)
	{
		List<Menu> menu_list=new ArrayList<Menu>();
		
		Iterator<Food> food_iterate=food_list.iterator();
		while(food_iterate.hasNext())
		{
			menu_list.add(menuRepository.findByMenuId(food_iterate.next().getMenu_id()));
		}
		return menu_list;
	}
	
	public List<Restaurant> getRestaurantList(List<Menu> menu_list)
	{
		List<Restaurant> restaurant_list=new ArrayList<Restaurant>();
		
		Iterator<Menu> menu_iterator=menu_list.iterator();
		while(menu_iterator.hasNext())
		{
			restaurant_list.add(restaurantRepository.findByRestaurantId(menu_iterator.next().getRestaurant_id()));
		}
		
		return restaurant_list;
	}
	
	public List<Restaurant> get_restaurant_with_distance(List<Restaurant> restaurant_list, double latitude,double longitude,int distance)
	{
		double actual_distance;
		RestaurantRecord restaurantRecord=new RestaurantRecord();
		List<Restaurant> restaurant_new_list=new ArrayList<Restaurant>();
		Iterator<Restaurant> restaurant_iterator=restaurant_list.iterator();
		while(restaurant_iterator.hasNext())
		{
			Restaurant restaurant_object=restaurant_iterator.next();
			restaurantRecord=restaurantrecordRepository.findRestaurantRecord(restaurant_object.getRestaurant_id());
			actual_distance=get_the_distance(latitude,longitude,restaurantRecord.getLatitude(),restaurantRecord.getLongitude());
			if((int)actual_distance>distance)
			{
				restaurant_new_list.add(restaurant_object);
				distance_map.put(restaurant_object.getRestaurant_id(),(float)distance);
			}
		}
		return restaurant_new_list;
		
	}
	private static double get_the_distance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			return dist;
		}
	}
}
	
	
	

