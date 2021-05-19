package com.digital.payments.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digital.payments.product.model.PaypalCredential;

public interface PaypalCredentialRepository extends JpaRepository<PaypalCredential, Long>{

	@Query(value = "SELECT * FROM paypal_credentials WHERE product=?1", nativeQuery = true)
	PaypalCredential findByProduct(String product);
}
