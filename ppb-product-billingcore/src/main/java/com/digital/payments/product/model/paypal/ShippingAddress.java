package com.digital.payments.product.model.paypal;

public class ShippingAddress {

	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "ShippingAddress [address=" + address + "]";
	}
}
