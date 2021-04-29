package com.digital.payments.product.model.paypal;

public class Address {

	private String addressLine1;
	private String adminArea1;
	private String adminArea2;
	private String postalCode;
	private String countryCode;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAdminArea1() {
		return adminArea1;
	}

	public void setAdminArea1(String adminArea1) {
		this.adminArea1 = adminArea1;
	}

	public String getAdminArea2() {
		return adminArea2;
	}

	public void setAdminArea2(String adminArea2) {
		this.adminArea2 = adminArea2;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "Address [addressLine1=" + addressLine1 + ", adminArea1=" + adminArea1 + ", adminArea2=" + adminArea2
				+ ", postalCode=" + postalCode + ", countryCode=" + countryCode + "]";
	}
}
