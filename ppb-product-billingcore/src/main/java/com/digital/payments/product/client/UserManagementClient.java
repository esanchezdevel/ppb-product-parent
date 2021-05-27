package com.digital.payments.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.digital.payments.product.model.dto.SubscribeRequestDTO;
import com.digital.payments.product.model.dto.SubscribeResponseDTO;

import feign.Headers;

@FeignClient(name = "usermanagement", url = "${user.management.url}")
public interface UserManagementClient {

	@PostMapping("")
	@Headers({"Content-Type: application/json", "Accept: application/json"})
	SubscribeResponseDTO subscribe(@RequestBody SubscribeRequestDTO subscribeRequestDTO);
}
