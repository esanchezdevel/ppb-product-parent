package com.digital.payments.product.paypal;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.payments.product.http.util.BasicAuthenticationHeader;
import com.digital.payments.product.httpclient.Post;
import com.digital.payments.product.httpclient.model.HttpClientRequest;
import com.digital.payments.product.httpclient.model.HttpClientResponse;
import com.digital.payments.product.model.PaypalCredential;
import com.digital.payments.product.model.paypal.PaypalAccessTokenResponse;
import com.digital.payments.product.repository.PaypalCredentialRepository;
import com.google.gson.Gson;

@Component
public class PaypalAccessToken {

	private static final Logger logger = LoggerFactory.getLogger(PaypalAccessToken.class);
	
	public static String accessToken;
	
	private String product;

	private Post post = new Post();

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
		HttpClientResponse httpClientResponse = post.execute(httpClientRequest);
		
		logger.debug("response: " + httpClientResponse);
		PaypalAccessTokenResponse response = new Gson().fromJson(httpClientResponse.getBody(), PaypalAccessTokenResponse.class);
		
		PaypalAccessToken.accessToken = response.getAccessToken();
		
		return response.getAccessToken();
	}

	public void setProduct(String product) {
		this.product = product;
	}
}
