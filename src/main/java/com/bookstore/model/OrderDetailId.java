package com.bookstore.model;

import java.io.Serializable;

public class OrderDetailId implements Serializable{
	private String isbn;

    private int order_id;

    // default constructor
    public OrderDetailId() {
    	
    }

    public OrderDetailId(int order_id, String isbn) {
        this.order_id = order_id;
        this.isbn = isbn;
    }

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
    
}
