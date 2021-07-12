package com.digital.payments.product.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.digital.payments.product.interceptor.StatsInterceptor;
import com.digital.payments.product.jpa.repository.TransactionRepository;
import com.digital.payments.product.service.HoroscopeService;
import com.digital.payments.product.service.SubscribeService;

@WebMvcTest
public class HoroscopeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StatsInterceptor statsInterceptor;
	
	@MockBean
	private TransactionRepository transactionRepository;
	
	@MockBean
	private SubscribeService subscribeService;
	
	@MockBean
	private HoroscopeService horoscopeService;
	
	@Test
	@DisplayName("test_index")
	void testIndex() throws Exception {
		
		when(statsInterceptor.preHandle(any(), any(), any())).thenReturn(true);
		
		String contentType = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8";
		
		mockMvc.perform(get("/horoscope").contentType(contentType))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
	}
	
}
