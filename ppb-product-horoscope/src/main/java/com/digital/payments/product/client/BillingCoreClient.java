package com.digital.payments.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.digital.payments.product.dto.SubscribeRequest;
import com.digital.payments.product.dto.SubscribeResponse;

@FeignClient(name = "billingcore", url = "${billingcore.url}")
public interface BillingCoreClient {

	@RequestMapping(method = RequestMethod.POST, value = "/subscribe")
	SubscribeResponse subscribe(SubscribeRequest subscribeRequest);
	
}
