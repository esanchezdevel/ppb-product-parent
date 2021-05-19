package com.digital.payments.product.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digital.payments.product.dto.BillingCoreRequest;
import com.digital.payments.product.dto.BillingCoreResponse;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionRequest;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;
import com.digital.payments.product.paypal.PaypalGetSubscription;
import com.digital.payments.product.service.SubscribeService;

@RestController
public class BillingCoreController {

	private static final Logger logger = LoggerFactory.getLogger(BillingCoreController.class);
	
	@Autowired
	private SubscribeService subscribeService;
	
	
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody BillingCoreRequest request) {
		
		logger.debug("Handling request: " + request);
		
		BillingCoreResponse response = new BillingCoreResponse();
		
		//TODO get subscriptionInfo from Paypal https://developer.paypal.com/docs/subscriptions/full-integration/subscription-management/
		subscribeService.execute(request);
		
		response.setTransactionStatus("SUBSCRIBED");
		return ResponseEntity.ok(response);
	}
}
