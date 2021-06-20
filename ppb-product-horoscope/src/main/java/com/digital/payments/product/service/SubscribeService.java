package com.digital.payments.product.service;

import com.digital.payments.product.dto.SubscribeRequest;
import com.digital.payments.product.dto.SubscribeResponse;

public interface SubscribeService {

	SubscribeResponse execute(SubscribeRequest request);
}
