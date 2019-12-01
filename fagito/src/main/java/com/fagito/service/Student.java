
package com.fagito.service;

import com.fagito.model.Customer_Discount;

public class Student extends Customer_Discount {
	
	public void set_rate(int rest_discount_rate,int admin_discount_rate)
	{
		this.rest_discount_rate=rest_discount_rate;
		this.admin_discount_rate=admin_discount_rate;
	}
}
