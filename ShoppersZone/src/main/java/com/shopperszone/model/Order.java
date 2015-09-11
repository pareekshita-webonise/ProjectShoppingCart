package com.shopperszone.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {


	private static final long serialVersionUID = 3505767563759053013L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "order_date")
	private String date;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ordered_items", joinColumns = {
			@JoinColumn(name = "order_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "item_id", nullable = false, updatable = false) })
	private List<Item> items;

	@Column(name = "total_amount")
	private double totalAmt;

	@Column(name = "payment_type")
	private String paymentType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String toString() {
		return this.getId() + " " + this.getDate() + " " + this.getUser().getFirstName() + " " + this.getTotalAmt()
				+ " " + this.getPaymentType();
	}
}