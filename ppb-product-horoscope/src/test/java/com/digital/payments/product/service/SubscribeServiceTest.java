package com.digital.payments.product.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digital.payments.product.client.BillingCoreClient;
import com.digital.payments.product.dto.SubscribeRequest;
import com.digital.payments.product.dto.SubscribeResponse;
import com.digital.payments.product.jpa.entity.Transaction;
import com.digital.payments.product.jpa.repository.TransactionRepository;

@SpringBootTest
public class SubscribeServiceTest {

	private static Transaction transaction;
	
	@MockBean
	private BillingCoreClient billingCoreClient;
	
	@MockBean
	private TransactionRepository transactionRepository;
	
	@Autowired
    private SubscribeService subscribe;
	
	@BeforeEach
	void setUp() {
		transaction = new Transaction();
		transaction.setId(new Long("1111111"));
		transaction.setUserEmail("test@test.com");
		transaction.setUserIp("1.1.1.1");
	}
	
	@Test
	@DisplayName("test_subscribe_success")
	void testSubscribeSuccess() {
	
		SubscribeRequest subscribeRequest = new SubscribeRequest();		
		subscribeRequest.setProduct("horoscope");
		subscribeRequest.setSign("tauro");
		subscribeRequest.setProductTransactionId(1L);
		subscribeRequest.setSubscriptionId("11111");
		
		SubscribeResponse subscribeResponse = new SubscribeResponse();
		subscribeResponse.setTransactionStatus("SUBSCRIBED");
		
		when(transactionRepository.getOne(anyLong())).thenReturn(transaction);
		when(billingCoreClient.subscribe(any())).thenReturn(subscribeResponse);
		
		SubscribeResponse response = subscribe.execute(subscribeRequest);
		
		assertNotNull(response);
		assertEquals("SUBSCRIBED", response.getTransactionStatus());
	}
	
	
	@Test
	@DisplayName("test_subscribe_error")
	void testSubscribeError() {
		SubscribeRequest subscribeRequest = new SubscribeRequest();		
		subscribeRequest.setProduct("horoscope");
		subscribeRequest.setSign("tauro");
		subscribeRequest.setProductTransactionId(1L);
		subscribeRequest.setSubscriptionId("11111");
		
		SubscribeResponse subscribeResponse = new SubscribeResponse();
		subscribeResponse.setTransactionStatus("ERROR");
		
		when(transactionRepository.getOne(anyLong())).thenReturn(transaction);
		when(billingCoreClient.subscribe(any())).thenReturn(subscribeResponse);
		
		SubscribeResponse response = subscribe.execute(subscribeRequest);
		
		assertNotNull(response);
		assertEquals("ERROR", response.getTransactionStatus());
	}
}
