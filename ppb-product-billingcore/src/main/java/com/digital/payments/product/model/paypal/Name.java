package com.digital.payments.product.model.paypal;

import com.google.gson.annotations.SerializedName;

public class Name {

	@SerializedName("given_name")
	private String givenName;
	
	private String surname;

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Name [givenName=" + givenName + ", surname=" + surname + "]";
	}
}
