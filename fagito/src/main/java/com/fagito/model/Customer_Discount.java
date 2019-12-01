package com.fagito.model;

public abstract class Customer_Discount {
	protected int rest_discount_rate;
	protected int admin_discount_rate;
	public abstract void set_rate(int rest_discout_rate,int admin_discount_rate);
	
	public float calculateDiscountRate(float actual_amount)
	{
		System.out.println(rest_discount_rate+" "+actual_amount);
		float discount_value=(float)((rest_discount_rate+admin_discount_rate)/100.0);
		float discount=actual_amount-(discount_value*actual_amount);
		System.out.println(discount);
		return discount;
	}

}
