package com.digital.payments.product.dto;

public class BillingCoreRequest {

	private String product;
	private long productTransactionId;
	private String subscriptionId;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public long getProductTransactionId() {
		return productTransactionId;
	}

	public void setProductTransactionId(long productTransactionId) {
		this.productTransactionId = productTransactionId;
	}
	
	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	@Override
	public String toString() {
		return "BillingCoreRequest [product=" + product + ", productTransactionId=" + productTransactionId
				+ ", subscriptionId=" + subscriptionId + "]";
	}
}
