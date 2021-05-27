package com.digital.payments.product.service;

import com.digital.payments.product.dto.RequestDTO;
import com.digital.payments.product.dto.ResponseDTO;

public interface ApiService {

	ResponseDTO execute(RequestDTO request);
}
