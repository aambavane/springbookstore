package com.bookstore;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.model.Book;
import com.bookstore.model.BookOrder;
import com.bookstore.services.BookOrderService;
import com.bookstore.services.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootApplication
public class BookStoreApplication {
	Logger logger = LoggerFactory.getLogger(BookStoreApplication.class);
	

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	
	
	/**
	 * This method is just to populate some sample books in the database.
	 * @param bookService
	 * @return
	 */
	/**
	@Bean
	public CommandLineRunner addBooks(BookService bookService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			logger.info("Adding books from book.json");

			TypeReference<List<Book>> bookTypeReference = new TypeReference<List<Book>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/book.json");
			try {
				List<Book> books = mapper.readValue(inputStream, bookTypeReference);
				bookService.addBooks(books);
			} catch (IOException e){
				logger.error("Unable to save books: " + e.getMessage(), e);
			}
		};
	}
	**/
	/**
	 * This method is just to populate some sample order in the database.
	 * @param bookOrderService
	 * @return
	 */
	/**
	@Bean
	public CommandLineRunner addOrder(BookOrderService bookOrderService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			logger.info("Adding order from order.json");

			TypeReference<BookOrder> orderTypeReference = new TypeReference<BookOrder>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/order.json");
			try {
				BookOrder order = mapper.readValue(inputStream, orderTypeReference);
				order.setPurchase_date(LocalDate.now());
				bookOrderService.placeOrder(order);
			} catch (IOException e){
				logger.error("Unable to save order: " + e.getMessage(), e);
			}
		};
	}**/

}