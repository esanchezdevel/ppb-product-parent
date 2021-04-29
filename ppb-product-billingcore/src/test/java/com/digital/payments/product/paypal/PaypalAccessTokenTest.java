package com.digital.payments.product.paypal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digital.payments.product.entity.PaypalCredential;
import com.digital.payments.product.httpclient.Post;
import com.digital.payments.product.httpclient.model.HttpClientResponse;
import com.digital.payments.product.repository.PaypalCredentialRepository;

@ExtendWith(MockitoExtension.class)
public class PaypalAccessTokenTest {

	private static final String PRODUCT = "horoscope";
	private static final String USERNAME = "Abkjx-knea2qohl6nluebZicwYKCYey_Qg99q_p5ibq6ZBRruXCpZIO1LpM2gcLuaeSszghMPKRfQyMO";
	private static final String PASSWORD = "EHpcd4AG0xKDZkGI4BH8DO2wPpkhNeAvTDAakwIHA-v4ncArvkx7UevI88ZIOXXVcxCE8o3a0gwQ3JLZ";
	private static final String ACCESS_TOKEN = "A21AAJ6GrKipZcl84yN0jVTJw7FOeJaxtlveHxHVCBPyhqPufYjaB6UTzF5H6k_ZTo-irh-vUSYJ9HHdX7MGcBs6-H_kOXZGA";
	
	private PaypalCredential paypalCredential;
	
	@Mock
	private Post post;
	
	@Mock
	private PaypalCredentialRepository paypalCredentialRepository;
	
	@InjectMocks
	private PaypalAccessToken paypalAccessToken;
	
	@BeforeEach
	void setUp() {
		paypalCredential = new PaypalCredential();
		paypalCredential.setProduct(PRODUCT);
		paypalCredential.setUsername(USERNAME);
		paypalCredential.setPassword(PASSWORD);
	}
	
	@Test
	@DisplayName("test_paypal_get_access_token")
	void testPaypalGetAccessToken() {
		
		String jsonResponse = "{\"scope\":\"https://uri.paypal.com/services/invoicing https://uri.paypal.com/services/vault/payment-tokens/read https://uri.paypal.com/services/disputes/read-buyer https://uri.paypal.com/services/payments/realtimepayment https://uri.paypal.com/services/disputes/update-seller https://uri.paypal.com/services/payments/payment/authcapture openid https://uri.paypal.com/services/disputes/read-seller Braintree:Vault https://uri.paypal.com/services/payments/refund https://api.paypal.com/v1/vault/credit-card https://api.paypal.com/v1/payments/.* https://uri.paypal.com/payments/payouts https://uri.paypal.com/services/vault/payment-tokens/readwrite https://api.paypal.com/v1/vault/credit-card/.* https://uri.paypal.com/services/subscriptions https://uri.paypal.com/services/applications/webhooks\",\"access_token\":\"A21AAJ6GrKipZcl84yN0jVTJw7FOeJaxtlveHxHVCBPyhqPufYjaB6UTzF5H6k_ZTo-irh-vUSYJ9HHdX7MGcBs6-H_kOXZGA\",\"token_type\":\"Bearer\",\"app_id\":\"APP-80W284485P519543T\",\"expires_in\":32399,\"nonce\":\"2021-04-23T11:01:39ZsDrGxYVM8rk7vKiFjEGGkwMPUim-2MlWYNcA3IL_0HQ\"}";

		HttpClientResponse response = new HttpClientResponse(200, jsonResponse);
		
		when(paypalCredentialRepository.findByProduct(any())).thenReturn(paypalCredential);
		when(post.execute(any())).thenReturn(response);
		
		String accessTokenResponse = paypalAccessToken.execute();
		
		assertAll(() -> {
			assertNotNull(accessTokenResponse, "The accessToken must not be null");
			assertEquals(ACCESS_TOKEN, accessTokenResponse, "Wrong access token value");
			assertEquals(ACCESS_TOKEN, PaypalAccessToken.accessToken, "Wrong access token value in global variable");
		});
	}
}
