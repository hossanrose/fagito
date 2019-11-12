package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu,String>{
	
	@Query(value="SELECT * FROM menu m where m.menu_id= :menu_id",nativeQuery=true)
    Menu findByMenuId(@Param("menu_id") String menu_id);
}
