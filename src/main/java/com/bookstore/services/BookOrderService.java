package com.bookstore.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.model.Book;
import com.bookstore.model.BookOrder;
import com.bookstore.model.OrderDetail;
import com.bookstore.repository.BookOrderRepository;

@Service
public class BookOrderService {
	
	@Autowired
	BookOrderRepository bookOrderRepository;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private BookService bookService;
	@Autowired
	private BookOrderService bookOrderService;
	
	public float getPreviousSales(int no, String period) throws Exception {
		
		switch(period) {
			case "month":
				return bookOrderRepository.findPreviousMonthSales(no);
			case "day":
				return bookOrderRepository.findPreviousDaySales(no);
			case "year":
				return bookOrderRepository.findPreviousYearSales(no);
			default:
				throw new Exception("Invalid period .. accepts month/day/year ");
				
		}
		
	}

	public BookOrder placeOrder(BookOrder order) throws Exception{
		List<Book> books = new ArrayList<Book>();
        try {
        	//Add BookOrder entry 
        	order.setPurchase_date(LocalDate.now());
        	BookOrder newOrder = bookOrderService.addOrder(order);
        	for(Book b : order.getBooksBought()) {
        		//Add order detail entry 
        		OrderDetail ordDetail = new OrderDetail();
        		ordDetail.setIsbn(b.getIsbn());
        		ordDetail.setQuantity(b.getNumOfCopies());
        		ordDetail.setOrder_id(newOrder.getOrder_id());
        		orderDetailService.addOrderDetail(ordDetail);
        		//Reduce qty for book from book inventory
        		books.add(bookService.sellBook(b));
        		
        	}
        	return order;
        } catch (Exception e) {
        	throw new Exception("Error placing order");
        	
        }
	}
	public BookOrder addOrder(BookOrder newOrder) {
		return bookOrderRepository.save(newOrder);
        
	}
}
