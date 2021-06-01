package com.digital.payments.product.model.paypal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

	@JsonProperty("address_line_1")
	private String addressLine1;
	
	@JsonProperty("admin_area_1")
	private String adminArea1;
	
	@JsonProperty("admin_area_2")
	private String adminArea2;
	
	@JsonProperty("postal_code")
	private String postalCode;
	
	@JsonProperty("country_code")
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
