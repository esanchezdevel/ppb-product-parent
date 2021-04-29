package com.digital.payments.product.model.paypal;

public class PaypalGetSubscriptionRequest {

	private String subscriptionId;
	private String product;
	
	public PaypalGetSubscriptionRequest(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "PaypalGetSubscriptionRequest [subscriptionId=" + subscriptionId + ", product=" + product + "]";
	}	
}
