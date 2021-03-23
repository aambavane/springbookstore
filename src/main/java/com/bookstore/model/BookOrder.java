package com.bookstore.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class BookOrder {
	@Id
	@Column(name = "order_id")   
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int order_id;
	private String customer_name;
	private LocalDate purchase_date;
	private float amount;
	@Transient
	private List<Book> booksBought;
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public LocalDate getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(LocalDate purchase_date) {
		this.purchase_date = purchase_date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public List<Book> getBooksBought() {
		return booksBought;
	}
	public void setBooksBought(List<Book> booksBought) {
		this.booksBought = booksBought;
	}
	
	
	
	

}
