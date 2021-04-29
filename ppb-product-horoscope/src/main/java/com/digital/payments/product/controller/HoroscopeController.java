package com.digital.payments.product.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.digital.payments.product.model.SubscribeRequest;
import com.digital.payments.product.model.SubscribeResponse;
import com.digital.payments.product.service.Subscribe;

@Controller
@RequestMapping("/horoscope")
public class HoroscopeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HoroscopeController.class);
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private Subscribe subscribe;
	
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
		
		logger.debug("Handling Subscribe Request: " + body.get("productTransactionId") + " subscriptionId: " + body.get("subscriptionId"));
		
		String sign = body.get("sign");
		
		Transaction transaction = transactionRepository.getOne(Long.parseLong(body.get("productTransactionId")));
		
		SubscribeRequest subscribeRequest = new SubscribeRequest();
		subscribeRequest.setTransactionId(transaction.getId());
		subscribeRequest.setSubscriptionId(body.get("subscriptionId"));
		SubscribeResponse subscribeResponse = subscribe.execute(subscribeRequest);
		
		logger.debug("subscribeResponse: " + subscribeResponse);
		
		if ("SUBSCRIBED".equals(subscribeResponse.getStatus())) {
			return ResponseEntity.ok("/horoscope/" + sign);	
		} else {
			return ResponseEntity.ok("/error");
		}
	}
	
	@GetMapping("/{sign}")
	public String product(@PathVariable String sign, Model model) {
		
		model.addAttribute("sign", sign);
		
		return "sign";
	}
}
