package com.digital.payments.product.model.dto;

public class SubscribeRequestDTO {

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

	public SubscribeRequestDTO() {
	}

	public SubscribeRequestDTO(String subscriptionId, String userId, String country, String planId, String productId,
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

	@Override
	public String toString() {
		return "SubscribeRequestDTO [subscriptionId=" + subscriptionId + ", userId=" + userId + ", country=" + country
				+ ", planId=" + planId + ", productId=" + productId + ", subscriptionDate=" + subscriptionDate
				+ ", name=" + name + ", surname=" + surname + ", payerId=" + payerId + ", shippingAddress="
				+ shippingAddress + ", price=" + price + ", currency=" + currency + ", status=" + status + "]";
	}

	public static class SubscribeRequestDTOBuilder {

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

		public SubscribeRequestDTOBuilder setSubscriptionId(String subscriptionId) {
			this.subscriptionId = subscriptionId;
			return this;
		}

		public SubscribeRequestDTOBuilder setUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public SubscribeRequestDTOBuilder setCountry(String country) {
			this.country = country;
			return this;
		}

		public SubscribeRequestDTOBuilder setPlanId(String planId) {
			this.planId = planId;
			return this;
		}

		public SubscribeRequestDTOBuilder setProductId(String productId) {
			this.productId = productId;
			return this;
		}

		public SubscribeRequestDTOBuilder setSubscriptionDate(String subscriptionDate) {
			this.subscriptionDate = subscriptionDate;
			return this;
		}

		public SubscribeRequestDTOBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public SubscribeRequestDTOBuilder setSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public SubscribeRequestDTOBuilder setPayerId(String payerId) {
			this.payerId = payerId;
			return this;
		}

		public SubscribeRequestDTOBuilder setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
			return this;
		}

		public SubscribeRequestDTOBuilder setPrice(String price) {
			this.price = price;
			return this;
		}

		public SubscribeRequestDTOBuilder setCurrency(String currency) {
			this.currency = currency;
			return this;
		}

		public SubscribeRequestDTOBuilder setStatus(String status) {
			this.status = status;
			return this;
		}

		public SubscribeRequestDTO build() {
			return new SubscribeRequestDTO(this.subscriptionId, this.userId, this.country, this.planId, this.productId,
					this.subscriptionDate, this.name, this.surname, this.payerId, this.shippingAddress, this.price,
					this.currency, this.status);
		}
	}
}
