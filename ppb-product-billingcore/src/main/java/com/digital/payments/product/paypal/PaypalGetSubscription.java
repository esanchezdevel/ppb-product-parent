package com.digital.payments.product.paypal;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.payments.product.client.PaypalClient;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionRequest;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;

@Component
public class PaypalGetSubscription {

	private static final Logger logger = LoggerFactory.getLogger(PaypalGetSubscription.class);
	
	@Autowired
	private PaypalAccessToken paypalAccessToken;
	
	@Autowired
	private PaypalClient paypalClient;

	public Optional<PaypalGetSubscriptionResponse> execute(PaypalGetSubscriptionRequest request, int limitRetries) {
		
		limitRetries--;
		
		if (limitRetries < 0) {
			logger.debug("Authentication retries limit reached: " + limitRetries);
			return Optional.empty();
		}
		
		logger.debug("Handle request " + limitRetries + ": " + request);
		
		if (PaypalAccessToken.accessToken == null) {
			paypalAccessToken.setProduct(request.getProduct());
			paypalAccessToken.execute();
		}

		//TODO retry when get 401 error
		PaypalGetSubscriptionResponse response = paypalClient.getSubscription("Bearer " + paypalAccessToken.accessToken, request.getSubscriptionId());
		
		return Optional.of(response);
	}
}
