package com.digital.payments.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digital.payments.product.data.Data;
import com.digital.payments.product.dto.SubscribeRequestDTO;
import com.digital.payments.product.dto.SubscribeResponseDTO;
import com.digital.payments.product.model.Subscription;
import com.digital.payments.product.repository.SubscriptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class SubscribeServiceTest {

	@MockBean
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private SubscribeService subscribeService;
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		this.objectMapper = new ObjectMapper();
	}
	
	@Test
	@DisplayName("test_subscribe")
	void testSubscribe() throws JsonMappingException, JsonProcessingException {
		
		Subscription subscription = new Subscription();
		
		SubscribeRequestDTO request = objectMapper.readValue(Data.SUBSCRIBE_REQUEST_JSON, SubscribeRequestDTO.class);
		
		when(subscriptionRepository.save(any())).thenReturn(subscription);
		
		SubscribeResponseDTO response = (SubscribeResponseDTO) subscribeService.execute(request);
		
		assertNotNull(response);
		assertEquals("success", response.getStatus());
		assertEquals("User subscribed", response.getDetails());
	}
}
