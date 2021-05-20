package com.digital.payments.product.model.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaypalAccessTokenResponse {

	private String scope;
	
	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("token_type")
	private String tokenType;
	
	@JsonProperty("app_id")
	private String appId;
	
	@JsonProperty("expires_in")
	private Integer expiresIn;
	
	private String nonce;

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	@Override
	public String toString() {
		return "PaypalAccessTokenResponse [scope=" + scope + ", accessToken=" + accessToken + ", tokenType=" + tokenType
				+ ", appId=" + appId + ", expiresIn=" + expiresIn + ", nonce=" + nonce + "]";
	}
}
