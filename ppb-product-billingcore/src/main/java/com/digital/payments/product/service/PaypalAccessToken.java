package com.digital.payments.product.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.payments.product.http.HttpClient;
import com.digital.payments.product.http.HttpClientResponse;

@Component
public class PaypalAccessToken implements Service {

	@Autowired
	private HttpClient httpClient;

	@Override
	public void execute() {

		Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		headers.put("Accept-Language", "es_US");

		String user = "Abkjx-knea2qohl6nluebZicwYKCYey_Qg99q_p5ibq6ZBRruXCpZIO1LpM2gcLuaeSszghMPKRfQyMO";
		String password = "EHpcd4AG0xKDZkGI4BH8DO2wPpkhNeAvTDAakwIHA-v4ncArvkx7UevI88ZIOXXVcxCE8o3a0gwQ3JLZ";
		String auth = user + ":" + password;

		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
		String authHeaderValue = "Basic " + new String(encodedAuth);

		headers.put("Authorization", authHeaderValue);

		Map<String, String> data = new HashMap<>();
		data.put("grant_type", "client_credentials");

		HttpClientResponse httpClientResponse = httpClient.get("https://api-m.sandbox.paypal.com/v1/oauth2/token",
				headers, data);

		System.out.println("TEST-response: " + httpClientResponse);
	}
}
