package com.fagito.dto;

public class CustomerDTO
{
	private String customer_id;
	private String customer_name;
	private int is_gold;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getIs_gold() {
		return is_gold;
	}
	public void setIs_gold(int is_gold) {
		this.is_gold = is_gold;
	}
	
	
	
}