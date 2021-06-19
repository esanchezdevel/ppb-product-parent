package com.digital.payments.product.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.digital.payments.product.client.exception.HandleClientError;

import feign.Request;
import feign.Response;

@SpringBootTest
public class HandleClientErrorTest {

	@Autowired
	private HandleClientError handleClientError;

	@Test
	@DisplayName("test_error_400")
	void testError400() {

		@SuppressWarnings("deprecation")
		Request request = Request.create("POST", "http://localhost:8080", new HashMap<String, Collection<String>>(),
				"test request".getBytes(), Charset.defaultCharset());

		Response response = Response.builder().status(400).reason("Bad Request")
				.headers(new HashMap<String, Collection<String>>()).body("test response", Charset.defaultCharset())
				.request(request).build();

		Exception exception = handleClientError.decode("test", response);

		assertTrue(exception instanceof ResponseStatusException);
		ResponseStatusException responseStatusException = (ResponseStatusException) exception;
		assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatus());
	}
	
	@Test
	@DisplayName("test_error_404")
	void testError404() {

		@SuppressWarnings("deprecation")
		Request request = Request.create("POST", "http://localhost:8080", new HashMap<String, Collection<String>>(),
				"test request".getBytes(), Charset.defaultCharset());

		Response response = Response.builder().status(404).reason("Not Found")
				.headers(new HashMap<String, Collection<String>>()).body("test response", Charset.defaultCharset())
				.request(request).build();

		Exception exception = handleClientError.decode("test", response);

		assertTrue(exception instanceof ResponseStatusException);
		ResponseStatusException responseStatusException = (ResponseStatusException) exception;
		assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatus());
	}
	
	@Test
	@DisplayName("test_error_401")
	void testError401() {

		@SuppressWarnings("deprecation")
		Request request = Request.create("POST", "http://localhost:8080", new HashMap<String, Collection<String>>(),
				"test request".getBytes(), Charset.defaultCharset());

		Response response = Response.builder().status(401).reason("Unauthorized")
				.headers(new HashMap<String, Collection<String>>()).body("test response", Charset.defaultCharset())
				.request(request).build();

		Exception exception = handleClientError.decode("test", response);

		assertTrue(exception instanceof ResponseStatusException);
		ResponseStatusException responseStatusException = (ResponseStatusException) exception;
		assertEquals(HttpStatus.UNAUTHORIZED, responseStatusException.getStatus());
	}
	
	@Test
	@DisplayName("test_error_other")
	void testErrorOther() {

		@SuppressWarnings("deprecation")
		Request request = Request.create("POST", "http://localhost:8080", new HashMap<String, Collection<String>>(),
				"test request".getBytes(), Charset.defaultCharset());

		Response response = Response.builder().status(500).reason("Internal Server Error")
				.headers(new HashMap<String, Collection<String>>()).body("test response", Charset.defaultCharset())
				.request(request).build();

		Exception exception = handleClientError.decode("test", response);

		assertTrue(exception instanceof ResponseStatusException);
		ResponseStatusException responseStatusException = (ResponseStatusException) exception;
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatusException.getStatus());
	}
}
