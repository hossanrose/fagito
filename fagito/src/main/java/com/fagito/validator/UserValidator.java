package com.fagito.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fagito.dto.*;

@Component
public class UserValidator
{
	@Value("${spring.user.email}")
	String errorEmail;
	@Value("${spring.user.password}")
	String errorPassword;
	public String validate(SignUpDTO signupDTO)
	{
		if(checkEmail(signupDTO.getEmail()))
		{
			if(checkPassword(signupDTO.getPassword()))
			{
				return null;
			}
			else
				return errorPassword;
		}
		else
			return errorEmail;
	}
	boolean checkEmail(String email)
	{
		if(Pattern.matches("[a-zA-Z0-9./]*@[a-z./]*[.][a-z]{2,3}", email))
		{
			return true;
		}
		return false;
	}
	
	boolean checkPassword(String password)
	{
		if(Pattern.matches("^.*(?=.{8,})((?=.*[!@#$%^&*()\\-_=+{};:,<.>]){1})(?=.*\\d)((?=.*[a-z]){1})((?=.*[A-Z]){1}).*$", password))
		{
			return true;
		}
		return false;
	}
	
}