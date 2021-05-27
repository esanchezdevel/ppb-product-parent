package com.digital.payments.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digital.payments.product.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

}
