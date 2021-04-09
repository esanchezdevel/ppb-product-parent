package com.digital.payments.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.payments.product.model.BillingCoreRequest;
import com.digital.payments.product.model.BillingCoreResponse;

@RestController
public class BillingCoreController {

	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestParam BillingCoreRequest request) {
		
		BillingCoreResponse response = new BillingCoreResponse();
		
		//TODO call to paypal API
		
		return ResponseEntity.ok(response);
	}
}
