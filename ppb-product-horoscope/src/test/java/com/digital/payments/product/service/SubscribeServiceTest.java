package com.digital.payments.product.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digital.payments.product.jpa.entity.Transaction;

@ExtendWith(MockitoExtension.class)
public class SubscribeServiceTest {

	private static Transaction transaction;
	
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
		

	}
	
	
	@Test
	@DisplayName("test_subscribe_error")
	@Disabled
	void testSubscribeError() {
		
	}
}
