package com.digital.payments.product.service;

import org.springframework.stereotype.Component;

import com.digital.payments.product.dto.BillingCoreRequest;
import com.digital.payments.product.dto.BillingCoreResponse;

@Component
public interface PpbService {

	BillingCoreResponse execute(BillingCoreRequest request);
}
