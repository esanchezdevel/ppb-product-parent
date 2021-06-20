package com.digital.payments.product.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.digital.payments.product.domain.Horoscope;
import com.digital.payments.product.dto.SubscribeRequest;
import com.digital.payments.product.dto.SubscribeResponse;
import com.digital.payments.product.jpa.entity.Transaction;
import com.digital.payments.product.jpa.repository.TransactionRepository;
import com.digital.payments.product.service.HoroscopeService;
import com.digital.payments.product.service.SubscribeService;

@Controller
@RequestMapping("/horoscope")
public class HoroscopeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HoroscopeController.class);
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private SubscribeService subscribeService;
	
	@Autowired
	private HoroscopeService horoscopeService;
	
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
	public ResponseEntity<?> subscribe(@RequestBody SubscribeRequest request, RedirectAttributes attributes) {
		
		logger.debug("Handling Subscribe Request: " + request.getProductTransactionId() + " subscriptionId: " + request.getSubscriptionId());
		
		String sign = request.getSign();
		
		SubscribeResponse subscribeResponse = subscribeService.execute(request);
		
		if ("SUBSCRIBED".equals(subscribeResponse.getTransactionStatus())) {
			return ResponseEntity.ok("/horoscope/" + sign);	
		} else {
			return ResponseEntity.ok("/error");
		}
	}
	
	@GetMapping("/{sign}")
	public String product(@PathVariable String sign, Model model) {
		
		model.addAttribute("sign", sign);
		
		Horoscope horoscope = horoscopeService.execute(sign);
		
		model.addAttribute("horoscope", horoscope);
		
		return "sign";
	}
}
