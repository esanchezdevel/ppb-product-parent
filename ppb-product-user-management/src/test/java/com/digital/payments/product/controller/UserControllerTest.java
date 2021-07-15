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

import com.digital.payments.product.data.Data;
import com.digital.payments.product.dto.SubscribeRequestDTO;
import com.digital.payments.product.dto.SubscribeResponseDTO;
import com.digital.payments.product.service.SubscribeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SubscribeService subscribeService;
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		this.objectMapper = new ObjectMapper();
	}
	
	@Test
	@DisplayName("test_subscribe")
	void testSubscribe() throws Exception {
		
		SubscribeRequestDTO subscribeRequest = objectMapper.readValue(Data.SUBSCRIBE_REQUEST_JSON, SubscribeRequestDTO.class);
		
		SubscribeResponseDTO subscribeResponse = new SubscribeResponseDTO();
		subscribeResponse.setStatus("success");
		subscribeResponse.setDetails("User subscribed");
		
		when(subscribeService.execute(any())).thenReturn(subscribeResponse);
		
		mockMvc.perform(post("/user-management/v1/subscribe").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(subscribeRequest)))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value("success"));
	}
}
