package com.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.model.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer>{  

}
