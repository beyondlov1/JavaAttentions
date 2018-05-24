package com.beyond.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ORDERS_9_3")
public class Order {
	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "uuid")
	private String id;

	@Column
	private String price;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
