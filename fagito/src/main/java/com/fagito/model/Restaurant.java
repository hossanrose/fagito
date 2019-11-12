package com.fagito.model;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restaurant")
public class Restaurant {
	
	@Id
	private String restaurant_id;
	private String restaurant_name;
	private Timestamp opening_time;
	private Timestamp closing_time;
	private String days;
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public Timestamp getOpening_time() {
		return opening_time;
	}
	public void setOpening_time(Timestamp opening_time) {
		this.opening_time = opening_time;
	}
	public Timestamp getClosing_time() {
		return closing_time;
	}
	public void setClosing_time(Timestamp closing_time) {
		this.closing_time = closing_time;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
}
