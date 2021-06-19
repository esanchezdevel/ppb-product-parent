package com.digital.payments.product.paypal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.payments.product.client.PaypalClient;
import com.digital.payments.product.http.util.BasicAuthenticationHeader;
import com.digital.payments.product.model.PaypalCredential;
import com.digital.payments.product.model.paypal.PaypalAccessTokenResponse;
import com.digital.payments.product.repository.PaypalCredentialRepository;

@Component
public class PaypalAccessToken {

	private static final Logger logger = LoggerFactory.getLogger(PaypalAccessToken.class);
	
	public static String accessToken;
	
	private String product;

	@Autowired
	private PaypalCredentialRepository paypalCredentialRepository;
	
	@Autowired
	private PaypalClient paypalClient;

	public String execute() {

		PaypalCredential paypalCredential = paypalCredentialRepository.findByProduct(product);

		if (paypalCredential == null) {
			logger.debug("ERROR! Credentials not found for product: " + this.product);
			return null;
		}
		
		String authenticationToken = BasicAuthenticationHeader.create(paypalCredential.getUsername(), paypalCredential.getPassword());
		PaypalAccessTokenResponse response = paypalClient.requestAccessToken(authenticationToken, "grant_type=client_credentials");
		
		logger.debug("Access Token: " + response);
		
		PaypalAccessToken.accessToken = response.getAccessToken();
		
		return response.getAccessToken();
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	public String getProduct() {
		return this.product;
	}
}
