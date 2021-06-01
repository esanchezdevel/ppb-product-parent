package com.digital.payments.product.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.payments.product.client.UserManagementClient;
import com.digital.payments.product.model.dto.BillingCoreRequestDTO;
import com.digital.payments.product.model.dto.BillingCoreResponseDTO;
import com.digital.payments.product.model.dto.SubscribeRequestDTO;
import com.digital.payments.product.model.dto.SubscribeResponseDTO;
import com.digital.payments.product.model.mapper.SubscribeRequestMapper;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionRequest;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;
import com.digital.payments.product.paypal.PaypalAccessToken;
import com.digital.payments.product.paypal.PaypalGetSubscription;

@Service
public class SubscribeService implements PpbService {

	private static final Logger logger = LoggerFactory.getLogger(SubscribeService.class);
	
	private static final Integer RETRIES = 3;
	
	@Autowired
	private PaypalAccessToken paypalAccessToken;

	@Autowired
	private PaypalGetSubscription paypalGetSubscription;
	
	@Autowired
	private UserManagementClient userManagementClient;
	
	@Override
	public BillingCoreResponseDTO execute(BillingCoreRequestDTO request) {
		
		paypalAccessToken.setProduct(request.getProduct());
		
		PaypalGetSubscriptionRequest paypalGetSubscriptionRequest = new PaypalGetSubscriptionRequest(request.getSubscriptionId(), request.getProduct());
		Optional<PaypalGetSubscriptionResponse> paypalGetSubscriptionResponse = paypalGetSubscription.execute(paypalGetSubscriptionRequest, RETRIES);
		
		String status = null;
		if (paypalGetSubscriptionResponse.isPresent()) {
			logger.debug("response: " + paypalGetSubscriptionResponse.get().toString());
			
			SubscribeRequestDTO subscribeRequestDTO = SubscribeRequestMapper.mapToDTO(paypalGetSubscriptionResponse.get(), request.getProduct());
			SubscribeResponseDTO subscribeResponseDTO = userManagementClient.subscribe(subscribeRequestDTO);
			
			logger.debug("usermanagement response: " + subscribeResponseDTO);
			if ("success".equals(subscribeResponseDTO.getStatus())) {
				status = "SUBSCRIBED";
			} else {
				status = "ERROR";
			}
		} else {
			logger.debug("no response from paypal");
			status = "ERROR";
		}
		return new BillingCoreResponseDTO(status);
	}

}
