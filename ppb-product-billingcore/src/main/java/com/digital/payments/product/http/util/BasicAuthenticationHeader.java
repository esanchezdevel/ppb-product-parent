package com.digital.payments.product.http.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthenticationHeader {

	public static String create(String user, String password) {
		
		String auth = user + ":" + password;

		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
		return "Basic " + new String(encodedAuth);
	}
}
