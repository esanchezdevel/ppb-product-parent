package com.digital.payments.product.service;

import org.springframework.stereotype.Component;

import com.digital.payments.product.model.BillingCoreRequest;
import com.digital.payments.product.model.BillingCoreResponse;

@Component
public interface Service {

	BillingCoreResponse execute(BillingCoreRequest request);
}
