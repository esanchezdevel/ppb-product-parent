package com.digital.payments.product.service;

public interface PpbService<T, R> {

	R execute(T obj);
}
