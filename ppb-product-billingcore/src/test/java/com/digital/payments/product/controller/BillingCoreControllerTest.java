package com.digital.payments.product.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.digital.payments.product.model.dto.BillingCoreRequestDTO;
import com.digital.payments.product.model.dto.BillingCoreResponseDTO;
import com.digital.payments.product.service.SubscribeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class BillingCoreControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SubscribeService subscribeService;
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
	}
	
	@Test
	@DisplayName("test_subscribe_endpoint")
	void testSubscribe() throws JsonProcessingException, Exception {
		
		BillingCoreRequestDTO request = new BillingCoreRequestDTO();
		request.setProduct("horoscope");
		request.setProductTransactionId(1L);
		request.setSubscriptionId("1111");
		
		BillingCoreResponseDTO billingCoreResponse = new BillingCoreResponseDTO("SUBSCRIBED");
		
		when(subscribeService.execute(any())).thenReturn(billingCoreResponse);
		
		mockMvc.perform(post("/subscribe").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.transactionStatus").value("SUBSCRIBED"));
	}
}
