package com.digital.payments.product.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.payments.product.dto.BillingCoreRequest;
import com.digital.payments.product.dto.BillingCoreResponse;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionRequest;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;
import com.digital.payments.product.paypal.PaypalAccessToken;
import com.digital.payments.product.paypal.PaypalGetSubscription;

@Service
public class SubscribeService implements PpbService {

	private static final Logger logger = LoggerFactory.getLogger(SubscribeService.class);
	
	private static final Integer RETRIES = 3;
	
	@Autowired
	private PaypalAccessToken paypalAccessToken;

	@Autowired
	private PaypalGetSubscription paypalGetSubscription;
	
	@Override
	public BillingCoreResponse execute(BillingCoreRequest request) {
		
		paypalAccessToken.setProduct(request.getProduct());
		String accessToken = paypalAccessToken.execute();
		
		PaypalGetSubscriptionRequest paypalGetSubscriptionRequest = new PaypalGetSubscriptionRequest(request.getSubscriptionId(), "horoscope");
		Optional<PaypalGetSubscriptionResponse> paypalGetSubscriptionResponse = paypalGetSubscription.execute(paypalGetSubscriptionRequest, RETRIES);
		
		if (paypalGetSubscriptionResponse.isPresent()) {
			logger.debug("response: " + paypalGetSubscriptionResponse.get().toString());
			
			//TODO request to usermanagment microservice subscribe
		} else {
			logger.debug("no response from paypal");
		}
		//TODO return the DTO
		return null;
	}

}
