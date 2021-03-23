package com.bookstore.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.model.Book;
import com.bookstore.model.BookOrder;
import com.bookstore.model.OrderDetail;
import com.bookstore.services.BookOrderService;
import com.bookstore.services.BookService;
import com.bookstore.services.OrderDetailService;

@RestController 
public class BookOrderController extends Service{
	
	 Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookOrderService bookOrderService;
	
	@GetMapping("book/getPreviousSales/{no}/{period}")
	public ResponseEntity<Object> getPreviousSales(@PathVariable int no, @PathVariable String period){
		logger.info(" API call for getPreviousSales for last "+no +" "+period);
		if(period == null || period.isEmpty() || no <= 0) {
			return sendErrorMessage("Input parameters not passed correctly , need period (month/day/year) and no(int)", HttpStatus.CONFLICT);
		}
		try {
			float previousSales = bookOrderService.getPreviousSales(no, period);
			return new ResponseEntity<>(previousSales, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}
	
	//TODO: Handle the issue where if some part of the order fails then the inventory should not be depleted for any 
	//books and error message shown to fail entire order
	@PostMapping("placeOrder")
    public ResponseEntity<Object> placeOrder(@RequestBody BookOrder order){
		logger.info("Place order for books ");
		BookOrder newOrder;
		try {
			newOrder = bookOrderService.placeOrder(order);
			if(newOrder != null)
				return new ResponseEntity<>("Successfully created Order", HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return sendErrorMessage("Error placing order", HttpStatus.CONFLICT);
		
    }
	


}
