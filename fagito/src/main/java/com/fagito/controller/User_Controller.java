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
    	String result;
    	CustomerDTO customerDTO=new CustomerDTO();
    	SignUpDTO signupDTO=new SignUpDTO();
    	BeanUtils.copyProperties(customerForm, customerDTO);
    	BeanUtils.copyProperties(customerForm, signupDTO);
    	result=userService.addUser(customerDTO,signupDTO);
	    return result;
	    
    }
    
   /*@GetMapping
    public String verifyUser(@RequestBody LoginForm loginForm)
    {
    	String result;
    	LoginDTO loginDTO=new LoginDTO();
    	BeanUtils.copyProperties(loginForm, loginDTO);
    	result=userService.verifyUser(loginDTO);
    	return result;
    }*/
}