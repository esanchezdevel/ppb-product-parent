package com.digital.payments.product.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digital.payments.product.dto.BillingCoreRequest;
import com.digital.payments.product.dto.BillingCoreResponse;
import com.digital.payments.product.paypal.PaypalAccessToken;

@ExtendWith(MockitoExtension.class)
public class SubscribeServiceTest {

	@Mock
	private PaypalAccessToken paypalAccessToken;
	
	@InjectMocks
	private SubscribeService subscribe;
	
	@Test
	@DisplayName("test_subscribe_success")
	void testSubscribeSuccess() {
		
		String tokenResponse = "";
		
		BillingCoreRequest request = new BillingCoreRequest();
		request.setProduct("horoscope");
		request.setProductTransactionId(111111);
		
		when(paypalAccessToken.execute()).thenReturn(tokenResponse);
		// TODO mock the paypal subscribe request
		
		BillingCoreResponse response = subscribe.execute(request);
	}
}
