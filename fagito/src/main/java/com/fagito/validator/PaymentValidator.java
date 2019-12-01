package com.fagito.validator;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

@Component
public class PaymentValidator {
	
	public boolean verify_account(long acc_no)
	{
		String acc=String.valueOf(acc_no);
		if(acc.length()==12)
			return true;
		else
			return false;
	}

	@SuppressWarnings("deprecation")
	public boolean verify_date(int month,int year)
	{
		Date date=new Date();
		if(year>=date.getYear())
		{
			if(month>=date.getMonth())
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean verify_cvv(int cvv)
	{
		String cvv_value=String.valueOf(cvv);
		if(cvv_value.length()==3)
			return true;
		
		return false;
	}
}
