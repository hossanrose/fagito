package com.fagito.model;

public abstract class Customer_Discount {
	protected int discount_rate;
	public abstract void set_rate(int discout_rate);
	
	public float calculateDiscountRate(float actual_amount)
	{
		System.out.println(discount_rate+" "+actual_amount);
		float discount_value=(float)(discount_rate/100.0);
		float discount=actual_amount-(discount_value*actual_amount);
		System.out.println(discount);
		return discount;
	}

}
