package com.digital.payments.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.digital.payments.product.jpa.entity.BaseEntity;

@Entity
@Table(name = "subscriptions")
public class Subscription extends BaseEntity {

	@Column(name = "subscription_id")
	private String subscriptionId;

	@Column(name = "user_id")
	private String userId;

	private String country;

	@Column(name = "plan_id")
	private String planId;

	@Column(name = "product_id")
	private String productId;

	@Column(name = "subscription_date")
	private String subscriptionDate;

	private String name;

	private String surname;

	@Column(name = "payer_id")
	private String payerId;

	@Column(name = "shipping_address")
	private String shippingAddress;

	private String price;

	private String currency;

	private String status;
	
	
	public Subscription() {

	}

	public Subscription(String subscriptionId, String userId, String country, String planId, String productId,
			String subscriptionDate, String name, String surname, String payerId, String shippingAddress, String price,
			String currency, String status) {
		this.subscriptionId = subscriptionId;
		this.userId = userId;
		this.country = country;
		this.planId = planId;
		this.productId = productId;
		this.subscriptionDate = subscriptionDate;
		this.name = name;
		this.surname = surname;
		this.payerId = payerId;
		this.shippingAddress = shippingAddress;
		this.price = price;
		this.currency = currency;
		this.status = status;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static class SubscriptionBuilder {
		
		private String subscriptionId;
		private String userId;
		private String country;
		private String planId;
		private String productId;
		private String subscriptionDate;
		private String name;
		private String surname;
		private String payerId;
		private String shippingAddress;
		private String price;
		private String currency;
		private String status;

		public SubscriptionBuilder setSubscriptionId(String subscriptionId) {
			this.subscriptionId = subscriptionId;
			return this;
		}

		public SubscriptionBuilder setUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public SubscriptionBuilder setCountry(String country) {
			this.country = country;
			return this;
		}

		public SubscriptionBuilder setPlanId(String planId) {
			this.planId = planId;
			return this;
		}

		public SubscriptionBuilder setProductId(String productId) {
			this.productId = productId;
			return this;
		}

		public SubscriptionBuilder setSubscriptionDate(String subscriptionDate) {
			this.subscriptionDate = subscriptionDate;
			return this;
		}

		public SubscriptionBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public SubscriptionBuilder setSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public SubscriptionBuilder setPayerId(String payerId) {
			this.payerId = payerId;
			return this;
		}

		public SubscriptionBuilder setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
			return this;
		}

		public SubscriptionBuilder setPrice(String price) {
			this.price = price;
			return this;
		}

		public SubscriptionBuilder setCurrency(String currency) {
			this.currency = currency;
			return this;
		}

		public SubscriptionBuilder setStatus(String status) {
			this.status = status;
			return this;
		}
		
		public Subscription build() {
			return new Subscription(this.subscriptionId, 
					this.userId,
					this.country,
					this.planId,
					this.productId,
					this.subscriptionDate,
					this.name,
					this.surname,
					this.payerId,
					this.shippingAddress,
					this.price,
					this.currency,
					this.status);
		}
	}
}
