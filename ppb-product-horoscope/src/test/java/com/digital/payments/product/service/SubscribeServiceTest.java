package com.digital.payments.product.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digital.payments.product.client.BillingCoreClient;
import com.digital.payments.product.dto.SubscribeRequest;
import com.digital.payments.product.dto.SubscribeResponse;
import com.digital.payments.product.jpa.entity.Transaction;
import com.digital.payments.product.jpa.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class SubscribeServiceTest {

	private static Transaction transaction;
	
	@Mock
	private BillingCoreClient billingCoreClient;
	
	@Mock
	private TransactionRepository transactionRepository;
	
	@InjectMocks
    private SubscribeService subscribe;
	
	@BeforeAll
	static void setUp() {
		transaction = new Transaction();
		transaction.setId(new Long("1111111"));
		transaction.setUserEmail("test@test.com");
		transaction.setUserIp("1.1.1.1");
	}
	
	@Test
	@DisplayName("test_subscribe_success")
	@Disabled
	void testSubscribeSuccess() {
	
		SubscribeRequest subscribeRequest = new SubscribeRequest();		
		subscribeRequest.setProduct("horoscope");
		subscribeRequest.setSign("tauro");
		subscribeRequest.setProductTransactionId(1L);
		subscribeRequest.setSubscriptionId("11111");
		
		SubscribeResponse subscribeResponse = new SubscribeResponse();
		subscribeResponse.setTransactionStatus("SUBSCRIBED");
		
		when(transactionRepository.getOne(anyLong())).thenReturn(transaction);
		when(billingCoreClient.subscribe(subscribeRequest)).thenReturn(subscribeResponse);
		
		SubscribeResponse response = subscribe.execute(subscribeRequest);
		
		assertNotNull(response);
		assertEquals("SUBSCRIBED", response.getTransactionStatus());
	}
	
	
	@Test
	@DisplayName("test_subscribe_error")
	@Disabled
	void testSubscribeError() {
		SubscribeRequest subscribeRequest = new SubscribeRequest();		
		subscribeRequest.setProduct("horoscope");
		subscribeRequest.setSign("tauro");
		subscribeRequest.setProductTransactionId(1L);
		subscribeRequest.setSubscriptionId("11111");
		
		SubscribeResponse subscribeResponse = new SubscribeResponse();
		subscribeResponse.setTransactionStatus("ERROR");
		
		when(transactionRepository.getOne(anyLong())).thenReturn(transaction);
		when(billingCoreClient.subscribe(subscribeRequest)).thenReturn(subscribeResponse);
		
		SubscribeResponse response = subscribe.execute(subscribeRequest);
		
		assertNotNull(response);
		assertEquals("ERROR", response.getTransactionStatus());
	}
}
