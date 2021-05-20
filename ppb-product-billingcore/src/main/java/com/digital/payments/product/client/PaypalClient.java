package com.digital.payments.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.digital.payments.product.model.paypal.PaypalAccessTokenResponse;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;

import feign.Body;
import feign.Headers;

@FeignClient(name = "paypal", url = "${paypal.api.url}")
public interface PaypalClient {

	@PostMapping("/oauth2/token")
	@Headers({"Content-Type: application/x-www-form-urlencoded", "Accept: application/json", "Accept-Language: es_US"})
	PaypalAccessTokenResponse requestAccessToken(@RequestHeader("Authorization") String token, @RequestBody String grantType);
	
	@GetMapping("/billing/subscriptions/{subscriptionId}")
	PaypalGetSubscriptionResponse getSubscription(@RequestHeader("Authorization") String bearerToken, @PathVariable("subscriptionId") String subscriptionId);
}
