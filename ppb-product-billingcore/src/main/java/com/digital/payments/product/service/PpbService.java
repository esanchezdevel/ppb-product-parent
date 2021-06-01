package com.digital.payments.product.service;

import org.springframework.stereotype.Component;

import com.digital.payments.product.model.dto.BillingCoreRequestDTO;
import com.digital.payments.product.model.dto.BillingCoreResponseDTO;

@Component
public interface PpbService {

	BillingCoreResponseDTO execute(BillingCoreRequestDTO request);
}
