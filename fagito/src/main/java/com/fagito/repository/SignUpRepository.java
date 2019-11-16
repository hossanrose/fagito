package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fagito.model.SignUp;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp,String>{
	
	@Query(value="SELECT count(1) FROM sign_up s where s.email = :email and s.password= :password",nativeQuery=true)	 
	Integer findByEmail(@Param("email") String email,@Param("password") String password);
	
	@Query(value="SELECT s.sign_up_id FROM sign_up s ORDER BY s.sign_up_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
	
	@Query(value="SELECT s.user_id FROM sign_up s where s.email = :email and s.password= :password",nativeQuery=true)	 
	String getByEmail(@Param("email") String email,@Param("password") String password);
}