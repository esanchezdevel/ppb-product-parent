package com.digital.payments.product.dto;

public class SubscribeRequest {

	private String sign;
	private String product;
	private long productTransactionId;
	private String subscriptionId;
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

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
		return "SubscribeRequest [sign=" + sign + ", product=" + product + ", productTransactionId="
				+ productTransactionId + ", subscriptionId=" + subscriptionId + "]";
	}
}
