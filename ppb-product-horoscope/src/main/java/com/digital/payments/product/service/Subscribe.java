package com.digital.payments.product.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.digital.payments.product.http.HttpClient;
import com.digital.payments.product.http.HttpClientResponse;
import com.digital.payments.product.model.SubscribeRequest;
import com.digital.payments.product.model.SubscribeResponse;

public class Subscribe implements Service<SubscribeRequest, SubscribeResponse> {

	@Autowired
	private HttpClient httpClient;
	
	private static final String url = "http://ppb-product-billingcore/subscribe";
	
	private Map<String, String> headers;
	private Map<String, String> data;
	
	@Override
	public SubscribeResponse execute(SubscribeRequest request) {
		
		SubscribeResponse response = new SubscribeResponse();
		
		data = new HashMap<>();
		data.put("product", "horosocope");
		data.put("productTransactionId", String.valueOf(request.getTransactionId()));
		
		HttpClientResponse httpClientResponse = httpClient.post(url, headers, data);
		
		if (httpClientResponse.getCode() == 200) {
			response.setStatus("SUBSCRIBED");
		} else {
			response.setStatus("ERROR");
		}
		
		return response;
	}
}
