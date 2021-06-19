package com.digital.payments.product.paypal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.digital.payments.product.client.PaypalClient;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionRequest;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;
import com.google.gson.Gson;

@SpringBootTest
public class PaypalGetSubscriptionTest {

	@MockBean
	private PaypalAccessToken paypalAccessToken;
	
	@MockBean
	private PaypalClient paypalClient;
	
	@Autowired
	@SpyBean
	private PaypalGetSubscription paypalGetSubscription;
	
	private static PaypalGetSubscriptionRequest request;
	
	private static Gson gson;
	
	private static final String PRODUCT = "horoscope";
	private static final String SUBSCRIPTION_ID = "I-MLDRBHTVWH8C";
	private static final String ACCESS_TOKEN = "A21AAJ6GrKipZcl84yN0jVTJw7FOeJaxtlveHxHVCBPyhqPufYjaB6UTzF5H6k_ZTo-irh-vUSYJ9HHdX7MGcBs6-H_kOXZGA";
	
	private static final String PAYPAL_RESPONSE = "{\n" + 
			"	\"status\": \"ACTIVE\",\n" + 
			"	\"status_update_time\": \"2021-04-29T10:30:08Z\",\n" + 
			"	\"id\": \"I-MLDRBHTVWH8C\",\n" + 
			"	\"plan_id\": \"P-6GX20855UD727592LMCBLDUA\",\n" + 
			"	\"start_time\": \"2021-04-29T10:29:22Z\",\n" + 
			"	\"quantity\": \"1\",\n" + 
			"	\"shipping_amount\": {\n" + 
			"		\"currency_code\": \"EUR\",\n" + 
			"		\"value\": \"0.0\"\n" + 
			"	},\n" + 
			"	\"subscriber\": {\n" + 
			"		\"email_address\": \"sb-jyjhc6032596@personal.example.com\",\n" + 
			"		\"payer_id\": \"XPVF4AKJSJ7NQ\",\n" + 
			"		\"name\": {\n" + 
			"			\"given_name\": \"John\",\n" + 
			"			\"surname\": \"Doe\"\n" + 
			"		},\n" + 
			"		\"shipping_address\": {\n" + 
			"			\"address\": {\n" + 
			"				\"address_line_1\": \"calle Vilamar� 76993- 17469\",\n" + 
			"				\"admin_area_2\": \"Albacete\",\n" + 
			"				\"admin_area_1\": \"Albacete\",\n" + 
			"				\"postal_code\": \"02001\",\n" + 
			"				\"country_code\": \"ES\"\n" + 
			"			}\n" + 
			"		}\n" + 
			"	},\n" + 
			"	\"billing_info\": {\n" + 
			"		\"outstanding_balance\": {\n" + 
			"			\"currency_code\": \"EUR\",\n" + 
			"			\"value\": \"0.0\"\n" + 
			"		},\n" + 
			"		\"cycle_executions\": [{\n" + 
			"			\"tenure_type\": \"TRIAL\",\n" + 
			"			\"sequence\": 1,\n" + 
			"			\"cycles_completed\": 1,\n" + 
			"			\"cycles_remaining\": 0,\n" + 
			"			\"current_pricing_scheme_version\": 1,\n" + 
			"			\"total_cycles\": 1\n" + 
			"		}, {\n" + 
			"			\"tenure_type\": \"REGULAR\",\n" + 
			"			\"sequence\": 2,\n" + 
			"			\"cycles_completed\": 0,\n" + 
			"			\"cycles_remaining\": 0,\n" + 
			"			\"current_pricing_scheme_version\": 1,\n" + 
			"			\"total_cycles\": 0\n" + 
			"		}],\n" + 
			"		\"next_billing_time\": \"2021-05-06T10:00:00Z\",\n" + 
			"		\"failed_payments_count\": 0\n" + 
			"	},\n" + 
			"	\"create_time\": \"2021-04-29T10:30:08Z\",\n" + 
			"	\"update_time \": \"2021 - 04 - 29 T10: 30: 08 Z \",\n" + 
			"	\"plan_overridden \": false,\n" + 
			"	\"links \": [{\n" + 
			"		\"href \": \"https: //api.sandbox.paypal.com/v1/billing/subscriptions/I-MLDRBHTVWH8C/cancel\",\n" + 
			"		\"rel\": \"cancel\",\n" + 
			"		\"method\": \"POST\"\n" + 
			"	}, {\n" + 
			"		\"href\": \"https://api.sandbox.paypal.com/v1/billing/subscriptions/I-MLDRBHTVWH8C\",\n" + 
			"		\"rel\": \"edit\",\n" + 
			"		\"method\": \"PATCH\"\n" + 
			"	}, {\n" + 
			"		\"href\": \"https://api.sandbox.paypal.com/v1/billing/subscriptions/I-MLDRBHTVWH8C\",\n" + 
			"		\"rel\": \"self\",\n" + 
			"		\"method\": \"GET\"\n" + 
			"	}, {\n" + 
			"		\"href\": \"https://api.sandbox.paypal.com/v1/billing/subscriptions/I-MLDRBHTVWH8C/suspend\",\n" + 
			"		\"rel\": \"suspend\",\n" + 
			"		\"method\": \"POST\"\n" + 
			"	}, {\n" + 
			"		\"href\": \"https://api.sandbox.paypal.com/v1/billing/subscriptions/I-MLDRBHTVWH8C/capture\",\n" + 
			"		\"rel\": \"capture\",\n" + 
			"		\"method\": \"POST\"\n" + 
			"	}]\n" + 
			"}";
	
	@BeforeAll
	static void setUp() {
		gson = new Gson();
		request = new PaypalGetSubscriptionRequest(SUBSCRIPTION_ID, PRODUCT);
	}
	
	@Test
	@DisplayName("test_get_subscription_success")
	void testGetSubscriptionSuccess() {
		
		PaypalGetSubscriptionResponse paypalGetSubscriptionResponse = gson.fromJson(PAYPAL_RESPONSE, PaypalGetSubscriptionResponse.class);
		
		when(paypalAccessToken.execute()).thenReturn(ACCESS_TOKEN);
		when(paypalClient.getSubscription(anyString(), anyString())).thenReturn(paypalGetSubscriptionResponse);
		
		Optional<PaypalGetSubscriptionResponse> response = paypalGetSubscription.execute(request, 3);
		
		assertAll(() -> {
			assertNotNull(response.get(), "The Optional response must not be null");
			assertEquals(SUBSCRIPTION_ID, response.get().getId(), "Wrong subscriptionId received");
			assertEquals("ACTIVE", response.get().getStatus(), "Wrong subscription status received");
		});
	}
	
	@Test
	@DisplayName("test_get_subscription_unauthorized")
	void testGetSubscriptionUnauthorized() {
		
		when(paypalAccessToken.execute()).thenReturn(ACCESS_TOKEN);
		when(paypalClient.getSubscription(anyString(), anyString())).thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "unauthorized error"));
		
		Optional<PaypalGetSubscriptionResponse> response = paypalGetSubscription.execute(request, 3);
		
		assertAll(() -> {
			assertFalse(response.isPresent(), "The Optional response must not be null");
		});
		
		verify(paypalAccessToken, times(3)).execute();
	}
	
	@Test
	@DisplayName("test_reach_limit")
	void testReachLimit() {
		
		Optional<PaypalGetSubscriptionResponse> response = paypalGetSubscription.execute(request, 0);
		
		assertFalse(response.isPresent());
	}
	
	@Test
	@DisplayName("test_get_subscription_works_in_retry")
	void testGetSubscriptionWorksInRetry() {
		
		PaypalGetSubscriptionResponse paypalGetSubscriptionResponse = gson.fromJson(PAYPAL_RESPONSE, PaypalGetSubscriptionResponse.class);
				
		Optional<PaypalGetSubscriptionResponse> paypalOptionalResponse = Optional.of(paypalGetSubscriptionResponse);
		
		//first request
		when(paypalAccessToken.execute()).thenReturn(ACCESS_TOKEN);
		when(paypalClient.getSubscription(anyString(), anyString())).thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "unauthorized error"));
		
		//second request		
		doReturn(paypalOptionalResponse).when(paypalGetSubscription).execute(any(), eq(2));
		
		Optional<PaypalGetSubscriptionResponse> response = paypalGetSubscription.execute(request, 3);
		
		assertTrue(response.isPresent());		
	}
}
