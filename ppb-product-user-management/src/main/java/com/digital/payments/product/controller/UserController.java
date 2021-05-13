package com.digital.payments.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.payments.product.dto.SubscribeRequest;
import com.digital.payments.product.dto.SubscribeResponse;
import com.digital.payments.product.service.SubscribeService;

@RestController
@RequestMapping("/user-management/v1")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private SubscribeService subscribeService;
	
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody SubscribeRequest request) {
		
		logger.debug("request: " + request);
		
		SubscribeResponse response = (SubscribeResponse) subscribeService.execute(request);
		
		return ResponseEntity.ok(response);
	}
}