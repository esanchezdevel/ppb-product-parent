package com.digital.payments.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/horoscope")
public class HoroscopeController {
	
	@GetMapping
	public String index(Model model) {
		
		return "index";
	}
}