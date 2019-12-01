package com.fagito.service;

import com.fagito.model.*;

public class GetCustomerFactory {

		public Customer_Discount getCustomer(String user_reference)
		{
			System.out.println(user_reference);
			if(user_reference==null)
				return null;
			
			if(user_reference.equals("S"))
			{
				System.out.println("Student");
				return new Student();
			}
			else if(user_reference.equals("C"))
				return new GeneralCustomer();
			
			return null;
		}
}
