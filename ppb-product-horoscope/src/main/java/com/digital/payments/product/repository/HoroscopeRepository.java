package com.digital.payments.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digital.payments.product.domain.Horoscope;

@Repository
public interface HoroscopeRepository extends JpaRepository<Horoscope, Long>{

	@Query(value = "SELECT * FROM horoscope WHERE sign = ?1 ORDER BY publish_date DESC", nativeQuery = true)
	Horoscope findCurrentContent(String sign);
}
