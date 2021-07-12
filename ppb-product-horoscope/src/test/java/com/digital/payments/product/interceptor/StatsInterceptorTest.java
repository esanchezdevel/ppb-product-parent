package com.digital.payments.product.interceptor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digital.payments.product.jpa.entity.Visit;
import com.digital.payments.product.jpa.repository.VisitRepository;

@SpringBootTest
public class StatsInterceptorTest {

	@MockBean
	private VisitRepository visitRepository;
	
	@MockBean
	private HttpServletRequest request;
	
	@MockBean
	private HttpServletResponse response;
	
	@Autowired
	private StatsInterceptor statsInterceptor;
	
	@Test
	@DisplayName("test_prehandle")
	void testPreHandle() throws Exception {
		
		Visit visit = new Visit();
		when(visitRepository.save(any())).thenReturn(visit);
		
		boolean result = statsInterceptor.preHandle(request, response, new Object());
		
		assertTrue(result);
	}
}
