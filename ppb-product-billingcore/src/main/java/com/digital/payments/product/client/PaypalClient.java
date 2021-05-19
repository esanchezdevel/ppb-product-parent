package com.digital.payments.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;

@FeignClient(name = "paypal", url = "${paypal.api.url}")
public interface PaypalClient {

	@GetMapping("/{subscriptionId}")
	PaypalGetSubscriptionResponse getSubscription(@RequestHeader("Authorization") String bearerToken, @PathVariable("subscriptionId") String subscriptionId);
}
