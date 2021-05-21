package com.digital.payments.product.paypal;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.digital.payments.product.client.PaypalClient;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionRequest;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;

/**
 * 
 * @author Enrique Sanchez Jordan
 * 
 * This class request a token to paypal if there is no previous token 
 * stored in memory.
 * 
 * When the token is present then makes the getSubscription request to 
 * Paypal. If get unauthorized error, retry the process 3 times requesting
 * a new authorization token
 *
 */
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

		PaypalGetSubscriptionResponse response = null;
		try {
			response = paypalClient.getSubscription("Bearer " + PaypalAccessToken.accessToken, request.getSubscriptionId());
		} catch (ResponseStatusException e) {
			logger.error("ResponseStatusException captured. " + e.getStatus());
			
			if (isRetryNeeded(limitRetries, e)) {
				logger.debug("retry needed. retrying request");
				
				PaypalAccessToken.accessToken = null;
				
				Optional<PaypalGetSubscriptionResponse> optionalResponse = execute(request, limitRetries);
				
				if (optionalResponse.isPresent()) {
					response = optionalResponse.get();
				}
			}
		}
		return parseResponseToOptional(response);
	}

	private Optional<PaypalGetSubscriptionResponse> parseResponseToOptional(PaypalGetSubscriptionResponse response) {
		if (response == null) {
			return Optional.empty();
		} else {
			return Optional.of(response);	
		}
	}

	private boolean isRetryNeeded(int limitRetries, ResponseStatusException e) {
		return e.getStatus() == HttpStatus.UNAUTHORIZED && limitRetries > 0;
	}
}
