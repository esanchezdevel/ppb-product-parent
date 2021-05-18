package com.digital.payments.product.dto;

public class SubscribeResponse {

	private String transactionStatus;

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Override
	public String toString() {
		return "SubscribeResponse [transactionStatus=" + transactionStatus + "]";
	}
}
