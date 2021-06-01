package com.digital.payments.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digital.payments.product.model.dto.BillingCoreRequestDTO;
import com.digital.payments.product.model.dto.BillingCoreResponseDTO;
import com.digital.payments.product.service.SubscribeService;

@RestController
public class BillingCoreController {

	private static final Logger logger = LoggerFactory.getLogger(BillingCoreController.class);
	
	@Autowired
	private SubscribeService subscribeService;
	
	
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody BillingCoreRequestDTO request) {
		
		logger.debug("Handling request: " + request);
		
		
		
		//TODO get subscriptionInfo from Paypal https://developer.paypal.com/docs/subscriptions/full-integration/subscription-management/
		subscribeService.execute(request);
		
		BillingCoreResponseDTO response = new BillingCoreResponseDTO("SUBSCRIBED");
		return ResponseEntity.ok(response);
	}
}
