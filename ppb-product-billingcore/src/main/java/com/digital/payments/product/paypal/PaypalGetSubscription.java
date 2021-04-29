package com.digital.payments.product.paypal;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.payments.product.httpclient.Get;
import com.digital.payments.product.httpclient.model.HttpClientRequest;
import com.digital.payments.product.httpclient.model.HttpClientResponse;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionRequest;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;
import com.google.gson.Gson;

@Component
public class PaypalGetSubscription {

	private static final Logger logger = LoggerFactory.getLogger(PaypalGetSubscription.class);
	
	@Autowired
	private PaypalAccessToken paypalAccessToken;

	private Get get = new Get();
	
	public Optional<PaypalGetSubscriptionResponse> execute(PaypalGetSubscriptionRequest request, int limit) {
		
		limit--;
		
		if (limit < 0) {
			logger.debug("Authentication retries limit reached: " + limit);
			return Optional.empty();
		}
		
		logger.debug("Handle request " + limit + ": " + request);
		
		if (PaypalAccessToken.accessToken == null) {
			paypalAccessToken.setProduct(request.getProduct());
			paypalAccessToken.execute();
		}

		String url = "https://api-m.sandbox.paypal.com/v1/billing/subscriptions/" + request.getSubscriptionId();
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Bearer " + PaypalAccessToken.accessToken);
		
		HttpClientRequest httpClientRequest = new HttpClientRequest(url, headers, null);
		
		logger.debug("httpClientRequest: " + httpClientRequest);
		
		HttpClientResponse httpClientResponse = get.execute(httpClientRequest);
		
		
		PaypalGetSubscriptionResponse response;
		
		if (httpClientResponse.getCode() == 401) {
			logger.debug("Expired Access Token. Request new one");
			
			PaypalAccessToken.accessToken = null;
			return execute(request, limit);
		} else {
			response = new Gson().fromJson(httpClientResponse.getBody(), PaypalGetSubscriptionResponse.class);
		}
		return Optional.of(response);
	}
}
