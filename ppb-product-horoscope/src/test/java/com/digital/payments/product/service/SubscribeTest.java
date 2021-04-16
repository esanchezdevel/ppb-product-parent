package com.digital.payments.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digital.payments.product.http.HttpClient;
import com.digital.payments.product.http.HttpClientResponse;
import com.digital.payments.product.jpa.entity.Transaction;
import com.digital.payments.product.model.SubscribeRequest;
import com.digital.payments.product.model.SubscribeResponse;

@ExtendWith(MockitoExtension.class)
public class SubscribeTest {

	private static Transaction transaction;
	
	/*
	 * create mock of the autowired bean
	 */
	@Mock
	private HttpClient httpClient; 
	
	/*
	 * inject mocks in the class that we will call
	 */
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
		
		Map<String, String> data = new HashMap<>();
		data.put("product", "horosocope");
		data.put("productTransactionId", String.valueOf(transaction.getId()));
		
		HttpClientResponse httpClientResponse = new HttpClientResponse(200, "");
		when(httpClient.post("http://ppb-product-billingcore/subscribe", null, data)).thenReturn(httpClientResponse);
		
		SubscribeRequest request = new SubscribeRequest();
		request.setTransactionId(transaction.getId());
		
		/*
		 * request to subscribe.execute. Be careful!!! if we make a new Subscribe() then 
		 * it will not have the Mocks injected. So we must use the instance declared at the beginning 
		 * of the class with the @InjectMocks tag
		 */
		SubscribeResponse response = subscribe.execute(request);
		
		assertEquals("SUBSCRIBED", response.getStatus());
	}
	
	
	@Test
	@DisplayName("test_subscribe_error")
	void testSubscribeError() {
		
		Map<String, String> data = new HashMap<>();
		data.put("product", "horosocope");
		data.put("productTransactionId", String.valueOf(transaction.getId()));
		
		HttpClientResponse httpClientResponse = new HttpClientResponse(400, "");
		when(httpClient.post("http://ppb-product-billingcore/subscribe", null, data)).thenReturn(httpClientResponse);
		
		SubscribeRequest request = new SubscribeRequest();
		request.setTransactionId(transaction.getId());
		
		SubscribeResponse response = subscribe.execute(request);
		
		assertEquals("ERROR", response.getStatus());
	}
}
