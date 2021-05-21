package com.digital.payments.product.client.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * 
 * @author Enrique Sanchez Jordan
 * 
 * Class for capture http errors received in http requests
 * done with feign client
 * 
 */
@Component
public class HandleClientError implements ErrorDecoder {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Exception decode(String methodKey, Response response) {

		switch (response.status()) {
		case 400:
			logger.error("Bad Request error status [" + response.status() + "] methodKey [" + methodKey + "]");
			return new ResponseStatusException(HttpStatus.valueOf(response.status()),
					"Bad Request error " + response.status());
		case 404: {
			logger.error("Not Found error status [" + response.status() + "] methodKey [" + methodKey + "]");
			return new ResponseStatusException(HttpStatus.valueOf(response.status()),
					"Not Found error " + response.status());
		}
		case 401:
			logger.error("Unauthorized error status [" + response.status() + "] methodKey [" + methodKey + "]");
			return new ResponseStatusException(HttpStatus.valueOf(response.status()),
					"unauthorized error " + response.status());
		default:
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Error response");
		}
	}

}