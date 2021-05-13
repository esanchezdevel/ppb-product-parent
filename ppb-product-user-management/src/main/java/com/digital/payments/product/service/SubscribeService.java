package com.digital.payments.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.digital.payments.product.dto.Request;
import com.digital.payments.product.dto.Response;
import com.digital.payments.product.dto.SubscribeRequest;
import com.digital.payments.product.dto.SubscribeResponse;

@Service
public class SubscribeService implements ApiService {

	private static final Logger logger = LoggerFactory.getLogger(SubscribeService.class);
	
	@Override
	public Response execute(Request request) {
		SubscribeRequest subscribeRequest = (SubscribeRequest) request;
		
		SubscribeResponse response = new SubscribeResponse();
		
		//TODO implement subscribe
		response.setResult("success");
		
		return response;
	}

}
