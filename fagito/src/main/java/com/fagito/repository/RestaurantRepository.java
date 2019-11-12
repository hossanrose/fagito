package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,String>{
	
	@Query(value="SELECT * FROM restaurant r where r.restaurant_id= :restaurant_id",nativeQuery=true)
	Restaurant findByRestaurantId(@Param("restaurant_id") String restaurant_id);
}
