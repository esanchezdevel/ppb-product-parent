package com.digital.payments.product.model;

public class BillingCoreRequest {

	private String product;
	private long productTransactionId;

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

	@Override
	public String toString() {
		return "BillingCoreRequest [" + (product != null ? "product=" + product + ", " : "") + "productTransactionId="
				+ productTransactionId + "]";
	}
}
