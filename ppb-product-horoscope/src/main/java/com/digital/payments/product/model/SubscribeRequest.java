package com.digital.payments.product.model;

public class SubscribeRequest {

	private long transactionId;
	private String subscriptionId;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	@Override
	public String toString() {
		return "SubscribeRequest [transactionId=" + transactionId + ", subscriptionId=" + subscriptionId + "]";
	}
}
