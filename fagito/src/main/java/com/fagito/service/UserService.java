package com.fagito.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fagito.dto.CustomerDTO;
import com.fagito.dto.LoginDTO;
import com.fagito.dto.SignUpDTO;
import com.fagito.model.Customer;
import com.fagito.model.SignUp;
import com.fagito.model.Student_mail;
import com.fagito.repository.CustomerRepository;
import com.fagito.repository.SignUpRepository;
import com.fagito.repository.StudentMailRepository;
import com.fagito.validator.UserValidator;

@Service
@PropertySource("application.properties")
public class UserService
{
	@Value("${spring.user.already_exsist}")
	String errorRegister;
	@Value("${spring.user.successLogin}")
	String successLogin;
	@Value("${spring.user.wrongCredentials}")
	String wrong;
	@Value("${spring.user.successRegister}")
	String successRegister;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SignUpRepository signupRepository;
	@Autowired
	private StudentMailRepository studentmailRepository;
	@Autowired
	private UserValidator userValidator;
	/*@Autowired
	public UserService(UserValidator userValidator,CustomerRepository customerRepository,StudentMailRepository studentmailRepository,SignUpRepository signupRepository)
	{
		this.userValidator=userValidator;
		this.customerRepository=customerRepository;
		this.studentmailRepository=studentmailRepository;
		this.signupRepository=signupRepository;
	}*/
	
	public String addUser(CustomerDTO customerDTO,SignUpDTO signupDTO)
	{
		System.out.print("I am in swrvice");
		Customer customer_model=new Customer();
		SignUp signup_model=new SignUp();
		List<Student_mail> mail_list;
		int reference_id;
		int sign_up_id;
		
		String result=null,user_reference=null;
		//validating user details
		result=userValidator.validate(signupDTO);
		if(result!=null)
		{
			return result;
		}
		//check for exisisting user
		int exisiting_user=this.getUserByEmail(signupDTO.getEmail(),signupDTO.getPassword());
		if(exisiting_user==1)
		{
			return errorRegister;
		}
		
		mail_list=studentmailRepository.findAll();
		Pattern p = Pattern.compile("(.*)@(.*)[.](.*)[.](.*)");
		Matcher m = p.matcher(signupDTO.getEmail());
		if (m.matches()) {
			System.out.print(m.group(3));
		    String domain=m.group(3);
	        for (Student_mail mail : mail_list) 
	        {
	        	if(domain.equals(mail.getMail_domain()))
	        	{
	        		user_reference="S";
	        		break;
	        	}
	        }
		}
		if(user_reference==null)
        	user_reference="C";
		System.out.println("I am in swrvice 1111"+user_reference);
		String reference_id_string=customerRepository.findLastRecord();
		
		if(reference_id_string!=null)
			reference_id=Integer.parseInt(reference_id_string.substring(1));
		else
			reference_id=99;
		
		System.out.println(reference_id);
		
		String sign_up_id_string=signupRepository.findLastRecord();
		if(sign_up_id_string!=null)
			sign_up_id=Integer.parseInt(sign_up_id_string.substring(2));
		else
			sign_up_id=99;
		System.out.println(sign_up_id);
		customerDTO.setCustomer_id(user_reference+String.valueOf(reference_id+1));
		customerDTO.setIs_gold(0);
		
		BeanUtils.copyProperties(customerDTO, customer_model);
		
		signupDTO.setSign_up_id("SU"+String.valueOf(sign_up_id+1));
		signupDTO.setUser_id(customerDTO.getCustomer_id());
		
		BeanUtils.copyProperties(signupDTO, signup_model);
		
		
		customerRepository.save(customer_model);
		signupRepository.save(signup_model);
		return successRegister;
	}
	
	public int getUserByEmail(String email,String password)
	{
		
		int exsist_result=signupRepository.findByEmail(email,password);
		
		return exsist_result;
	}
	public String verifyUser(LoginDTO loginDTO)
	{
		int exsist_user=this.getUserByEmail(loginDTO.getEmail(),loginDTO.getPassword());
		if(exsist_user!=1)
		{
				return successLogin; 
		}
		return wrong;
	}
		
}	
		
