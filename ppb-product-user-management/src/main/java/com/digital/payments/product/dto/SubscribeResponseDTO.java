package com.digital.payments.product.dto;

public class SubscribeResponseDTO extends ResponseDTO {

	private String status;
	private String details;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "SubscribeResponseDTO [status=" + status + ", details=" + details + "]";
	}
}
