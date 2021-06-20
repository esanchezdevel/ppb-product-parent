package com.digital.payments.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.payments.product.domain.Horoscope;
import com.digital.payments.product.repository.HoroscopeRepository;

@Service
public class HoroscopeServiceImpl implements HoroscopeService {

	@Autowired
	private HoroscopeRepository horoscopeRepository;
	
	@Override
	public Horoscope execute(String sign) {
		
		Horoscope horoscope = horoscopeRepository.findCurrentContent(sign);
		
		return horoscope;
	}
}
