package com.digital.payments.product.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.digital.payments.product.http.util.ParameterStringBuilder;

@Component
public class HttpClient {

	private static final int TIMEOUT = 5000;
	
	private static HttpURLConnection connection;
	
	public HttpClientResponse get(String url, Map<String, String> headers, Map<String, String> data) {
	
		HttpClientResponse httpClientResponse = null;
				
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");

			/*
			 * set request headers
			 */
			headers.forEach((k, v) -> connection.setRequestProperty(k, v));
			
			/*
			 * set request body
			 */
			connection.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(ParameterStringBuilder.getParamsString(data));
			out.flush();
			out.close();

			connection.setConnectTimeout(TIMEOUT);
			connection.setReadTimeout(TIMEOUT);

			/*
			 * execute the request
			 */
			int responseCode = connection.getResponseCode();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			httpClientResponse = new HttpClientResponse(responseCode, content.toString());
		} catch (Exception e) {
			// TODO log error
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		return httpClientResponse;
	}
	
	public String post() {
		// TODO Not implemented
		return null;
	}
}
