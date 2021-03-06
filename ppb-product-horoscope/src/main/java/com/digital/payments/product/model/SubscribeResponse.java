package com.digital.payments.product.model;

public class SubscribeResponse {

	private String status;
	private String errorCode;
	private String errorDesc;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	@Override
	public String toString() {
		return "SubscribeResponse [" + (status != null ? "status=" + status + ", " : "")
				+ (errorCode != null ? "errorCode=" + errorCode + ", " : "")
				+ (errorDesc != null ? "errorDesc=" + errorDesc : "") + "]";
	}
}
