package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fagito.model.AdminSeasonalOffers;

@Repository
public interface AdminSeasonalOffersRepository extends JpaRepository<AdminSeasonalOffers, String>{
	
	@Query(value="SELECT a.offer_id FROM admin_seasonal_offers a ORDER BY a.offer_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
	
	@Query(value="SELECT * FROM admin_seasonal_offers a ORDER BY a.offer_id DESC LIMIT 1",nativeQuery=true)
	AdminSeasonalOffers findLatest();

}
