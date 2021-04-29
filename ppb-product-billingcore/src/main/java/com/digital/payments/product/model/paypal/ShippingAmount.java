package com.digital.payments.product.model.paypal;

import com.google.gson.annotations.SerializedName;

public class ShippingAmount {

	@SerializedName("currency_code")
	private String currencyCode;
	
	private float value;

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ShippingAmount [currencyCode=" + currencyCode + ", value=" + value + "]";
	}
}
