package com.digital.payments.product.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digital.payments.product.model.BillingCoreRequest;
import com.digital.payments.product.model.BillingCoreResponse;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionRequest;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;
import com.digital.payments.product.paypal.PaypalGetSubscription;

@RestController
public class BillingCoreController {

	private static final Logger logger = LoggerFactory.getLogger(BillingCoreController.class);
	
	@Autowired
	private PaypalGetSubscription paypalGetSubscription;
	
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody BillingCoreRequest request) {
		
		logger.debug("Handling request: " + request);
		
		BillingCoreResponse response = new BillingCoreResponse();
		
		//TODO get subscriptionInfo from Paypal https://developer.paypal.com/docs/subscriptions/full-integration/subscription-management/
		PaypalGetSubscriptionRequest paypalGetSubscriptionRequest = new PaypalGetSubscriptionRequest(request.getSubscriptionId());
		paypalGetSubscriptionRequest.setProduct(request.getProduct());
		Optional<PaypalGetSubscriptionResponse> paypalGetSubscriptionResponse = paypalGetSubscription.execute(paypalGetSubscriptionRequest, 3);
		
		if (paypalGetSubscriptionResponse.isPresent()) {
			//TODO subscribe to usersManagement
			logger.debug("Get Subscriptions Response: " + paypalGetSubscriptionResponse.get());
			
			response.setTransactionStatus("SUBSCRIBED"); //TODO change
		} else {
			logger.debug("No subscription found in Paypal");
			
			response.setTransactionStatus("ERROR");
		}
		
		return ResponseEntity.ok(response);
	}
}
