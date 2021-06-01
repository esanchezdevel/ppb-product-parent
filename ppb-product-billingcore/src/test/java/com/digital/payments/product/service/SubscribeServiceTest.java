package com.digital.payments.product.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digital.payments.product.client.UserManagementClient;
import com.digital.payments.product.data.Data;
import com.digital.payments.product.model.dto.BillingCoreRequestDTO;
import com.digital.payments.product.model.dto.BillingCoreResponseDTO;
import com.digital.payments.product.model.dto.SubscribeResponseDTO;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;
import com.digital.payments.product.paypal.PaypalAccessToken;
import com.digital.payments.product.paypal.PaypalGetSubscription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class SubscribeServiceTest {

	@MockBean
	private PaypalGetSubscription paypalGetSubscription;
	
	@MockBean
	private UserManagementClient userManagementClient;
	
	@MockBean
	private PaypalAccessToken paypalAccessToken;
	
	@Autowired
	private SubscribeService subscribe;
	
	ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
	}
	
	@Test
	@DisplayName("test_subscribe_success")
	void testSubscribeSuccess() throws JsonMappingException, JsonProcessingException {
		
		
		BillingCoreRequestDTO request = new BillingCoreRequestDTO();
		request.setProduct("horoscope");
		request.setProductTransactionId(111111);
		
		PaypalGetSubscriptionResponse paypalGetSubscriptionResponse = objectMapper.readValue(Data.PAYPAL_GETSUBSCRIPTION_RESPONSE, PaypalGetSubscriptionResponse.class);

		SubscribeResponseDTO subscribeResponseDTO = new SubscribeResponseDTO();
		subscribeResponseDTO.setStatus("success");
		subscribeResponseDTO.setDetails("User subscribed");
		
		doNothing().when(paypalAccessToken).setProduct(anyString());
		when(paypalGetSubscription.execute(any(), anyInt())).thenReturn(Optional.of(paypalGetSubscriptionResponse));
		when(userManagementClient.subscribe(any())).thenReturn(subscribeResponseDTO);
		
		BillingCoreResponseDTO response = subscribe.execute(request);
		
		assertNotNull(response);
		assertEquals("SUBSCRIBED", response.getTransactionStatus());
	}
}
