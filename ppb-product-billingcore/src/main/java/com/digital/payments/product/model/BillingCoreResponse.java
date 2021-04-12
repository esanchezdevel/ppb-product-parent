package com.digital.payments.product.model;

public class BillingCoreResponse {

	private String transactionStatus;

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Override
	public String toString() {
		return "BillingCoreResponse [" + (transactionStatus != null ? "transactionStatus=" + transactionStatus : "")
				+ "]";
	}
}
