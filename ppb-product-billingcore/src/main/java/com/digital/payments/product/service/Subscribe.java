package com.digital.payments.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.payments.product.model.BillingCoreRequest;
import com.digital.payments.product.model.BillingCoreResponse;
import com.digital.payments.product.paypal.PaypalAccessToken;

@Component
public class Subscribe implements Service {

	private static final Logger logger = LoggerFactory.getLogger(Subscribe.class);
	
	@Autowired
	private PaypalAccessToken paypalAccessToken;
	
	@Override
	public BillingCoreResponse execute(BillingCoreRequest request) {
		
		paypalAccessToken.setProduct(request.getProduct());
		String accessToken = paypalAccessToken.execute();
		
		
		// TODO subscribe with paypal
		
		return null;
	}

}
