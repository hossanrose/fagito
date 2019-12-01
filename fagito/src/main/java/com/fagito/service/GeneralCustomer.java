package com.fagito.model;

public class GeneralCustomer extends Customer_Discount {
	
	public void set_rate(int rest_discount_rate,int admin_discount_rate)
	{
		this.rest_discount_rate=rest_discount_rate;
		this.admin_discount_rate=admin_discount_rate;
	}

}
