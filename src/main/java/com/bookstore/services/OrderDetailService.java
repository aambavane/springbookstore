package com.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.model.OrderDetail;
import com.bookstore.repository.OrderDetailRepository;

@Service
public class OrderDetailService {
	
	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
        
	}

}
