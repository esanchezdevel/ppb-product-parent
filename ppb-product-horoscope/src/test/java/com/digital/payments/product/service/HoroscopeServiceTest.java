package com.digital.payments.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.digital.payments.product.domain.Horoscope;
import com.digital.payments.product.repository.HoroscopeRepository;

@SpringBootTest
public class HoroscopeServiceTest {

	private static final String SIGN = "tauro";
	
	@MockBean
	private HoroscopeRepository horoscopeRepository;
	
	@Autowired
	private HoroscopeService horoscopeService;
	
	@Test
	@DisplayName("test_execute")
	void testExecute() {
		
		Horoscope horoscopeTauro = new Horoscope();
		horoscopeTauro.setId(1L);
		horoscopeTauro.setPublishDate(new Date());
		horoscopeTauro.setSign(SIGN);
		horoscopeTauro.setText("This is a sample content");
		
		
		when(horoscopeRepository.findCurrentContent(SIGN)).thenReturn(horoscopeTauro);
		
		Horoscope horoscope = horoscopeService.execute(SIGN);
		
		assertNotNull(horoscope);
		assertEquals(SIGN, horoscope.getSign());
		assertEquals("This is a sample content", horoscope.getText());
	}
}
