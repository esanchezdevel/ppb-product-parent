package com.digital.payments.product.service;

public interface Service<T, R> {

	R execute(T obj);
}
