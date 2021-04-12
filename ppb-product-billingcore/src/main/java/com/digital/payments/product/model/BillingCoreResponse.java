package com.digital.payments.product.model;

public class BillingCoreResponse {

	private String product;
	private String productTransactionId;
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProductTransactionId() {
		return productTransactionId;
	}
	public void setProductTransactionId(String productTransactionId) {
		this.productTransactionId = productTransactionId;
	}
}
