package com.digital.payments.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/horoscope")
public class HoroscopeController {
	
	@GetMapping
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/lp/{sign}")
	public String landingPage(@PathVariable("sign") String sign, Model model) {
		
		model.addAttribute("sign", sign);
		model.addAttribute("productTransactionId", 1111111);
		
		return "landingPage";
	}
	
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody String body, Model model) {
		
		System.out.println("TEST-subscribe request: " + body);
		//TODO make request to billingCore
		
		//Redirect to product
		return ResponseEntity.ok("{\"status\":\"ok\"}");
	}
}
