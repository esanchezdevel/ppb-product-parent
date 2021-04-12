package com.digital.payments.product.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.payments.product.entity.PaypalCredential;
import com.digital.payments.product.http.HttpClient;
import com.digital.payments.product.http.HttpClientResponse;
import com.digital.payments.product.http.util.BasicAuthenticationHeader;
import com.digital.payments.product.repository.PaypalCredentialRepository;

@Component
public class PaypalAccessToken implements Service {

	private static final Logger logger = LoggerFactory.getLogger(PaypalAccessToken.class);
	
	private String product;

	@Autowired
	private HttpClient httpClient;

	@Autowired
	private PaypalCredentialRepository paypalCredentialRepository;

	@Override
	public void execute() {

		PaypalCredential paypalCredential = paypalCredentialRepository.findByProduct(product);

		if (paypalCredential == null) {
			logger.debug("ERROR! Credentials not found for product: " + this.product);
			return;
		}
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization",
				BasicAuthenticationHeader.create(paypalCredential.getUsername(), paypalCredential.getPassword()));
		headers.put("Accept", "application/json");
		headers.put("Accept-Language", "es_US");
		
		Map<String, String> data = new HashMap<>();
		data.put("grant_type", "client_credentials");

		HttpClientResponse httpClientResponse = httpClient.get("https://api-m.sandbox.paypal.com/v1/oauth2/token",
				headers, data);

		logger.debug("response: " + httpClientResponse);
	}

	public void setProduct(String product) {
		this.product = product;
	}
}
