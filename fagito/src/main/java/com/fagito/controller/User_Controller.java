package com.fagito.controller;

import com.fagito.dto.CustomerDTO;
import com.fagito.dto.LoginDTO;
import com.fagito.dto.SignUpDTO;
import com.fagito.dto.TokenDTO;
import com.fagito.service.UserService;
import com.fagito.view.CustomerForm;
import com.fagito.view.LoginForm;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class User_Controller{
    
	@Autowired
	private UserService userService;

    @PostMapping
    public String addUser(@RequestBody CustomerForm customerForm){
    	System.out.print("hi inside spring boot");
    	CustomerDTO customerDTO=new CustomerDTO();
    	SignUpDTO signupDTO=new SignUpDTO();
    	BeanUtils.copyProperties(customerForm, customerDTO);
    	BeanUtils.copyProperties(customerForm, signupDTO);
    	return userService.addUser(customerDTO,signupDTO);
	    
    }
    
   @PostMapping("/login")
    public ResponseEntity<?> verifyUser(@RequestBody LoginForm loginForm)
    {
    	try
    	{
    		System.out.println("Inside Login Service");
    		String exsist_user;
	    	LoginDTO loginDTO=new LoginDTO();
	    	BeanUtils.copyProperties(loginForm, loginDTO);
	    	exsist_user = userService.verifyUser(loginDTO);
	    	System.out.println(exsist_user);
	    	return ResponseEntity.ok().body(exsist_user);
	    }
    	catch(Exception e)
    	{
    		return ResponseEntity.status(500).body(e.getMessage());
    	}
    }
}