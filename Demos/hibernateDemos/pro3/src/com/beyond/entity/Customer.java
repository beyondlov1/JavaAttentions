package com.beyond.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Cacheable
@Entity
@Table(name = "CUSTOMER_9_3")
public class Customer {
	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "uuid")
	private String id;

	@Column
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Order> orders = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
