package com.digital.payments.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.digital.payments.product.jpa.entity.Transaction;
import com.digital.payments.product.jpa.repository.TransactionRepository;

@Controller
@RequestMapping("/horoscope")
public class HoroscopeController {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@GetMapping
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/lp/{sign}")
	public String landingPage(@PathVariable("sign") String sign, Model model) {
		
		Transaction transaction = transactionRepository.save(new Transaction());
		
		model.addAttribute("sign", sign);
		model.addAttribute("productTransactionId", transaction.getId());
		
		return "landingPage";
	}
	
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestParam Map<String, String> body, RedirectAttributes attributes) {
		
		System.out.println("TEST-subscribe request: " + body.get("productTransactionId"));
		
		String sign = body.get("sign");
		
		//TODO make request to billingCore
		Transaction transaction = transactionRepository.getOne(Long.parseLong(body.get("productTransactionId")));
		
		System.out.println("TEST-transaction from database: " + transaction);
		
		
		//Redirect to product
		//TODO if it's ok redirect to Arias, if not redirect to Error
		return ResponseEntity.ok("/horoscope/" + sign);
		
	}
	
	@GetMapping("/{sign}")
	public String product(@PathVariable String sign, Model model) {
		
		model.addAttribute("sign", sign);
		
		return "sign";
	}
}
