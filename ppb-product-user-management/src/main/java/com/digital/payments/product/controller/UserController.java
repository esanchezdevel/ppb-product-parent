package com.digital.payments.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.payments.product.dto.SubscribeRequest;
import com.digital.payments.product.dto.SubscribeResponse;

@RestController
@RequestMapping("/user-management/v1")
public class UserController {

	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(SubscribeRequest request) {
		
		SubscribeResponse response = new SubscribeResponse();
		response.setResult("success");
		
		return ResponseEntity.ok(response);
	}
}
