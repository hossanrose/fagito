package com.fagito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fagito.validator.PaymentValidator;
import com.fagito.view.Payment_Form_UI;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentValidator paymentValidator;
	
	public String verify_payment(Payment_Form_UI payment_form_ui) throws Exception
	{
		if(paymentValidator.verify_account(payment_form_ui.getAccount_no()))
		{
			if(paymentValidator.verify_date(payment_form_ui.getMonth(),payment_form_ui.getYear()))
			{
				if(paymentValidator.verify_cvv(payment_form_ui.getCvv()))
				{
					return "PAID00456712";
				}
			}
		}
		throw new Exception("Payment Failure");
	}

}
