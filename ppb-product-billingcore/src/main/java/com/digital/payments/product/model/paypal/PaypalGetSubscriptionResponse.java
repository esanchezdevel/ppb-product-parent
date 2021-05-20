package com.digital.payments.product.model.paypal;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaypalGetSubscriptionResponse {

	private String status;
	
	@JsonProperty("status_update_time")
	private Date statusUpdateTime;
	
	private String id;
	
	@JsonProperty("plan_id")
	private String planId;
	
	@JsonProperty("start_time")
	private Date startTime;
	
	private String quantity;
	
	@JsonProperty("shipping_amount")
	private ShippingAmount shippingAmount;
	
	private Subscriber subscriber;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusUpdateTime() {
		return statusUpdateTime;
	}

	public void setStatusUpdateTime(Date statusUpdateTime) {
		this.statusUpdateTime = statusUpdateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public ShippingAmount getShippingAmount() {
		return shippingAmount;
	}

	public void setShippingAmount(ShippingAmount shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public String toString() {
		return "PaypalGetSubscriptionResponse [status=" + status + ", statusUpdateTime=" + statusUpdateTime + ", id="
				+ id + ", planId=" + planId + ", startTime=" + startTime + ", quantity=" + quantity
				+ ", shippingAmount=" + shippingAmount + ", subscriber=" + subscriber + "]";
	}
}
