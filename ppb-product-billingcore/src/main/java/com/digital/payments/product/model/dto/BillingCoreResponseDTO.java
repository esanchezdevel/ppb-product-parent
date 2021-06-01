package com.digital.payments.product.model.dto;

public class BillingCoreResponseDTO {

	private String transactionStatus;

	public BillingCoreResponseDTO(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Override
	public String toString() {
		return "BillingCoreResponseDTO [" + (transactionStatus != null ? "transactionStatus=" + transactionStatus : "")
				+ "]";
	}
}
