package com.digital.payments.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digital.payments.product.jpa.entity.Visit;
import com.digital.payments.product.jpa.repository.VisitRepository;

@Controller
@RequestMapping("/horoscope")
public class HoroscopeController {

	@Autowired
	private VisitRepository visitRepository;
	
	@GetMapping
	public String index(Model model) {
		
		Visit visit = new Visit();
		visit.setUserIp("127.0.0.1");
		visit.setRoute("/horoscope");
		visit.setMethod("GET");
		visit.setCountry("es");
		visit.setUserAgent("test");
		visitRepository.save(visit);
		
		return "index";
	}
}
