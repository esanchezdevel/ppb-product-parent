package com.digital.payments.product.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.digital.payments.product.httpclient.Post;
import com.digital.payments.product.httpclient.model.HttpClientRequest;
import com.digital.payments.product.httpclient.model.HttpClientResponse;
import com.digital.payments.product.model.SubscribeRequest;
import com.digital.payments.product.model.SubscribeResponse;

@Component
public class Subscribe implements Service<SubscribeRequest, SubscribeResponse> {

	private static final Logger logger = LoggerFactory.getLogger(Subscribe.class);
	
	private static final String url = "http://ppb-product-billingcore:8080/subscribe";
	
	private Map<String, String> headers;
	private Map<String, Object> data;
	
	private Post post = new Post();
		
	@Override
	public SubscribeResponse execute(SubscribeRequest request) {
		
		logger.debug("Handling subscribe request: " + request);
		
		SubscribeResponse response = new SubscribeResponse();
		
		headers = new HashMap<>();
		headers.put("ContentType", "application/json");
		
		data = new HashMap<>();
		data.put("product", "horosocope");
		data.put("productTransactionId", request.getTransactionId());
		
		HttpClientRequest httpClientRequest = new HttpClientRequest(url, headers, data);
		HttpClientResponse httpClientResponse = post.execute(httpClientRequest);
	
		if (httpClientResponse.getCode() == 200) {
			response.setStatus("SUBSCRIBED");
		} else {
			response.setStatus("ERROR");
		}
		
		return response;
	}
}
