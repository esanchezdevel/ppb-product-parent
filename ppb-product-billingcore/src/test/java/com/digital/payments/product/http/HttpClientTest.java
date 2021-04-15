package com.digital.payments.product.http;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HttpClientTest {
	
	private static String url;
	private static Map<String, String> headers;
	private static Map<String, String> data;	
	
	@BeforeAll
	static void setUp() {
		url = "https://httpbin.org";
		headers = new HashMap<>();
		headers.put("Accept", "*/*");
		headers.put("Content-Type", "application/json");
		data = new HashMap<>();
		data.put("testParam", "testValue");
	}
	
	@Test
	@DisplayName("test_get_request")
	void testGetRequest() {
		HttpClient httpClient = new HttpClient();
		HttpClientResponse response = httpClient.get(url + "/get", headers, data);
		System.out.println(response.toString());
		assertEquals(200, response.getCode());
	}
	
	@Test
	@DisplayName("test_post_request")
	void testPostRequest() {
		HttpClient httpClient = new HttpClient();
		HttpClientResponse response = httpClient.post(url + "/post", headers, data);
		System.out.println(response.toString());
		assertEquals(200, response.getCode());
	}
}
