package com.digital.payments.product.model.paypal;

import com.google.gson.annotations.SerializedName;

public class Subscriber {

	@SerializedName("email_address")
	private String emailAddress;

	@SerializedName("payer_id")
	private String payerId;

	private Name name;

	@SerializedName("shipping_address")
	private ShippingAddress shippingAddress;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public String toString() {
		return "Subscriber [emailAddress=" + emailAddress + ", payerId=" + payerId + ", name=" + name
				+ ", shippingAddress=" + shippingAddress + "]";
	}
}
