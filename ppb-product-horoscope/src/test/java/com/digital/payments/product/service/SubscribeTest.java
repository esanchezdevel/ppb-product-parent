package com.digital.payments.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digital.payments.product.httpclient.Post;
import com.digital.payments.product.httpclient.model.HttpClientRequest;
import com.digital.payments.product.httpclient.model.HttpClientResponse;
import com.digital.payments.product.jpa.entity.Transaction;
import com.digital.payments.product.model.SubscribeRequest;
import com.digital.payments.product.model.SubscribeResponse;

@ExtendWith(MockitoExtension.class)
public class SubscribeTest {

	private static Transaction transaction;
	
	@Mock
	private Post post;
	
	@InjectMocks
    private Subscribe subscribe;
	
	@BeforeAll
	static void setUp() {
		transaction = new Transaction();
		transaction.setId(new Long("1111111"));
		transaction.setUserEmail("test@test.com");
		transaction.setUserIp("1.1.1.1");
	}
	
	@Test
	@DisplayName("test_subscribe_success")
	void testSubscribeSuccess() {
		
		String successResponse = "{\"result\" : \"ok\"}";
		
		Map<String, String> data = new HashMap<>();
		data.put("product", "horosocope");
		data.put("productTransactionId", String.valueOf(transaction.getId()));
		
		HttpClientResponse httpClientResponse = new HttpClientResponse(200, successResponse);
		lenient().when(post.execute(any(HttpClientRequest.class))).thenReturn(httpClientResponse);
		
		SubscribeRequest request = new SubscribeRequest();
		request.setTransactionId(transaction.getId());
		
		SubscribeResponse response = subscribe.execute(request);
		
		assertNotNull(response, () -> "Subscribe response must not be null");
		assertEquals("SUBSCRIBED", response.getStatus(), () -> "Wrong subscribe response status");
	}
	
	
	@Test
	@DisplayName("test_subscribe_error")
	void testSubscribeError() {
		
		Map<String, String> data = new HashMap<>();
		data.put("product", "horosocope");
		data.put("productTransactionId", String.valueOf(transaction.getId()));
		
		HttpClientResponse httpClientResponse = new HttpClientResponse(400, "");
		when(post.execute(any(HttpClientRequest.class))).thenReturn(httpClientResponse);
		
		SubscribeRequest request = new SubscribeRequest();
		request.setTransactionId(transaction.getId());
		
		SubscribeResponse response = subscribe.execute(request);
		
		assertNotNull(response, () -> "Subscribe response must not be null");
		assertEquals("ERROR", response.getStatus(), () -> "Wrong subscribe response status");
	}
}
