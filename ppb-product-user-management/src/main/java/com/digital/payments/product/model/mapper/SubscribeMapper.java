package com.digital.payments.product.model.mapper;

import com.digital.payments.product.dto.SubscribeRequestDTO;
import com.digital.payments.product.model.Subscription;
import com.digital.payments.product.model.Subscription.SubscriptionBuilder;

public class SubscribeMapper {

	public static Subscription mapDTOToEntity(SubscribeRequestDTO dto) {
		
		SubscriptionBuilder entityBuilder = new SubscriptionBuilder()
				.setSubscriptionId(dto.getSubscriptionId())
				.setUserId(dto.getUserId())
				.setCountry(dto.getCountry())
				.setPlanId(dto.getPlanId())
				.setProductId(dto.getProductId())
				.setSubscriptionDate(dto.getSubscriptionDate())
				.setName(dto.getName())
				.setSurname(dto.getSurname())
				.setPayerId(dto.getPayerId())
				.setShippingAddress(dto.getShippingAddress())
				.setPrice(dto.getPrice())
				.setCurrency(dto.getCurrency())
				.setStatus(dto.getStatus());
		
		return entityBuilder.build();
	}
}
