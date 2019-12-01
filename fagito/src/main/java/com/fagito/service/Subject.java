package com.fagito.service;

public interface Subject {
	
	public void Attach(Observer o);
	public void Dettach(Observer o);
	public void Notify() throws Exception;
	
}
