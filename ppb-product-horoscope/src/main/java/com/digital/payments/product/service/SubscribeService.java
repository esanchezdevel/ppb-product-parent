package com.digital.payments.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.payments.product.client.BillingCoreClient;
import com.digital.payments.product.dto.SubscribeRequest;
import com.digital.payments.product.dto.SubscribeResponse;
import com.digital.payments.product.jpa.entity.Transaction;
import com.digital.payments.product.jpa.repository.TransactionRepository;

@Service
public class SubscribeService implements PpbService<SubscribeRequest, SubscribeResponse> {

	private static final Logger logger = LoggerFactory.getLogger(SubscribeService.class);
	
	@Autowired
	private BillingCoreClient billingCoreClient;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public SubscribeResponse execute(SubscribeRequest request) {
		
		logger.debug("Handling subscribe request: " + request);
		
		Transaction transaction = transactionRepository.getOne(request.getProductTransactionId());
		
		SubscribeRequest subscribeRequest = new SubscribeRequest();
		subscribeRequest.setProduct("horoscope");
		subscribeRequest.setProductTransactionId(transaction.getId());
		subscribeRequest.setSubscriptionId(request.getSubscriptionId());
		
		SubscribeResponse subscribeResponse = billingCoreClient.subscribe(subscribeRequest);
		
		logger.debug("subscribeResponse: " + subscribeResponse);
		
		return subscribeResponse;
	}
}
