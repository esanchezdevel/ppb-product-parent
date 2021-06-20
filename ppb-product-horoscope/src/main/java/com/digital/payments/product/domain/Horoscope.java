package com.digital.payments.product.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.digital.payments.product.jpa.entity.BaseEntity;

@Entity
@Table(name = "horoscope")
public class Horoscope extends BaseEntity {

	@Column(name = "publish_date")
	private Date publishDate;
	
	private String sign;
	
	private String text;

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Horoscope [publishDate=" + publishDate + ", sign=" + sign + ", text=" + text + "]";
	}
}
