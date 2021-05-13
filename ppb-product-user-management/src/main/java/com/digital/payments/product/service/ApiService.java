package com.digital.payments.product.service;

import com.digital.payments.product.dto.Request;
import com.digital.payments.product.dto.Response;

public interface ApiService {

	Response execute(Request request);
}
