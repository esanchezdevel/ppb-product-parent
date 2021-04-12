package com.digital.payments.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digital.payments.product.model.BillingCoreRequest;
import com.digital.payments.product.model.BillingCoreResponse;
import com.digital.payments.product.service.PaypalAccessToken;

@RestController
public class BillingCoreController {

	@Autowired
	private PaypalAccessToken paypalAccessToken;
	
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody BillingCoreRequest request) {
		
		BillingCoreResponse response = new BillingCoreResponse();
		
		//TODO call to paypal API
		paypalAccessToken.setProduct(request.getProduct());
		paypalAccessToken.execute();
		
		response.setTransactionStatus("subscribed"); //TODO change
		
		return ResponseEntity.ok(response);
	}
}
