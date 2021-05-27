package com.digital.payments.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.payments.product.dto.RequestDTO;
import com.digital.payments.product.dto.ResponseDTO;
import com.digital.payments.product.dto.SubscribeRequestDTO;
import com.digital.payments.product.dto.SubscribeResponseDTO;
import com.digital.payments.product.model.Subscription;
import com.digital.payments.product.model.mapper.SubscribeMapper;
import com.digital.payments.product.repository.SubscriptionRepository;

@Service
public class SubscribeService implements ApiService {

	private static final Logger logger = LoggerFactory.getLogger(SubscribeService.class);
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Override
	public ResponseDTO execute(RequestDTO request) {
		SubscribeRequestDTO subscribeRequestDTO = (SubscribeRequestDTO) request;
		
		Subscription subscription = SubscribeMapper.mapDTOToEntity(subscribeRequestDTO);
		
		subscriptionRepository.save(subscription);
		
		SubscribeResponseDTO response = new SubscribeResponseDTO();
		
		response.setStatus("success");
		response.setDetails("User subscribed");
		
		return response;
	}

}
