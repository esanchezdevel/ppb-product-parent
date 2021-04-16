package com.digital.payments.product.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.digital.payments.product.http.util.ParameterStringBuilder;

@Component
public class HttpClient {

	private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
	
	private static final int TIMEOUT = 5000;
	
	private static HttpURLConnection connection;
	
	public HttpClientResponse get(String url, Map<String, String> headers, Map<String, String> data) {
	
		HttpClientResponse httpClientResponse = null;
		
		url = createUrl(url, data);
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod(HttpMethod.GET.name());
			
			/*
			 * set request headers
			 */
			headers.forEach((k, v) -> connection.setRequestProperty(k, v));
			
			connection.setConnectTimeout(TIMEOUT);
			connection.setReadTimeout(TIMEOUT);
			
			
			
			int responseCode = connection.getResponseCode();
			
			StringBuffer response = null;
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine;
				response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			}
			httpClientResponse = new HttpClientResponse(responseCode, response != null ? response.toString() : "");
		} catch (Exception e) {
			logger.error("Error in GET request", e);
		} finally {
			connection.disconnect();
		}
		return httpClientResponse;
	}
	
	public HttpClientResponse post(String url, Map<String, String> headers, Map<String, String> data) {
		HttpClientResponse httpClientResponse = null;
		try {
			URL obj = new URL(url);
			connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestMethod(HttpMethod.POST.name());
			
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
			
			int responseCode = connection.getResponseCode();
			
			StringBuffer response = null;
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine;
				response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			}
			httpClientResponse = new HttpClientResponse(responseCode, response != null ? response.toString() : "");
		} catch (Exception e) {
			logger.error("Error in GET request", e);
		} finally {
			connection.disconnect();
		}
		return httpClientResponse;
	}
	
	private String createUrl(String url, Map<String, String> data) {
		StringBuffer tmpUrl = new StringBuffer(url).append("?");
		for (Entry<String, String> entry : data.entrySet()) {
			try {
				tmpUrl.append(entry.getKey())
				.append("=")
				.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
				.append("&");
			} catch (UnsupportedEncodingException e) {
				logger.error("Error encoding param: " + entry.getKey() + " - " + entry.getValue(), e);
			}
		}
		return tmpUrl.toString();
	}
}
