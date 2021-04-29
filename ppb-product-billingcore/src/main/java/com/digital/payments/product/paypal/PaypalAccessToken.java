package com.digital.payments.product.paypal;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.payments.product.entity.PaypalCredential;
import com.digital.payments.product.http.util.BasicAuthenticationHeader;
import com.digital.payments.product.httpclient.Get;
import com.digital.payments.product.httpclient.model.HttpClientRequest;
import com.digital.payments.product.httpclient.model.HttpClientResponse;
import com.digital.payments.product.repository.PaypalCredentialRepository;

@Component
public class PaypalAccessToken {

	private static final Logger logger = LoggerFactory.getLogger(PaypalAccessToken.class);
	
	private String product;

	private Get get = new Get();

	@Autowired
	private PaypalCredentialRepository paypalCredentialRepository;

	public String execute() {

		PaypalCredential paypalCredential = paypalCredentialRepository.findByProduct(product);

		if (paypalCredential == null) {
			logger.debug("ERROR! Credentials not found for product: " + this.product);
			return null;
		}
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization",
				BasicAuthenticationHeader.create(paypalCredential.getUsername(), paypalCredential.getPassword()));
		headers.put("Accept", "application/json");
		headers.put("Accept-Language", "es_US");
		
		Map<String, Object> data = new HashMap<>();
		data.put("grant_type", "client_credentials");

		HttpClientRequest httpClientRequest = new HttpClientRequest("https://api-m.sandbox.paypal.com/v1/oauth2/token", headers, data);
		HttpClientResponse httpClientResponse = get.execute(httpClientRequest);
		

		logger.debug("response: " + httpClientResponse);
		return httpClientResponse.getBody();
	}

	public void setProduct(String product) {
		this.product = product;
	}
}