package com.digital.payments.product.model.mapper;

import com.digital.payments.product.model.dto.SubscribeRequestDTO;
import com.digital.payments.product.model.dto.SubscribeRequestDTO.SubscribeRequestDTOBuilder;
import com.digital.payments.product.model.paypal.PaypalGetSubscriptionResponse;

public class SubscribeRequestMapper {

	public static SubscribeRequestDTO mapToDTO(PaypalGetSubscriptionResponse input, String product) {
		
		SubscribeRequestDTOBuilder dtoBuilder = new SubscribeRequestDTOBuilder()
				.setSubscriptionId(input.getId())
				.setUserId(input.getSubscriber().getEmailAddress())
				.setCountry(input.getSubscriber().getShippingAddress().getAddress().getCountryCode())
				.setPlanId(input.getPlanId())
				.setProductId(product)
				.setSubscriptionDate(input.getStartTime().toString())
				.setName(input.getSubscriber().getName().getGivenName())
				.setSurname(input.getSubscriber().getName().getSurname())
				.setPayerId(input.getSubscriber().getPayerId())
				.setShippingAddress(input.getSubscriber().getShippingAddress().getAddress().getAddressLine1())
				.setPrice(String.valueOf(input.getShippingAmount().getValue()))
				.setCurrency(input.getShippingAmount().getCurrencyCode())
				.setStatus(input.getStatus());
		
		return dtoBuilder.build();
	}
}
