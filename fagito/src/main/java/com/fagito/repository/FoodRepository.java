package com.fagito.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,String>{
	
	@Query(value="SELECT * FROM food f where f.food_name like '%Pizza%'",nativeQuery=true)
	List<Food> findByName(@Param("food_name") String food_name);
}
