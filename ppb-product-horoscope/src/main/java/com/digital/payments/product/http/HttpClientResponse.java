package com.digital.payments.product.http;

public class HttpClientResponse {

	private int code;
	private String response;
	
	public HttpClientResponse(int code, String response) {
		this.code = code;
		this.response = response;
	}
	
	public int getCode() {
		return code;
	}
	public String getResponse() {
		return response;
	}

	@Override
	public String toString() {
		return "HttpClientResponse [code=" + code + ", " + (response != null ? "response=" + response : "") + "]";
	}
}
