package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.fagito.model.Student_mail;
@Repository
public interface StudentMailRepository extends JpaRepository<Student_mail,Integer>{
}