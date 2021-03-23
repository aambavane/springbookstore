package com.bookstore;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bookstore.model.Book;
import com.bookstore.services.BookOrderService;
import com.bookstore.services.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class BookStoreTests {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookOrderService bookOrderService;

	@Test
	public void getBookById() throws Exception {

		Book book = new Book();
		book.setIsbn("9788120340077");
		book.setAuthorName("Daniel Spielman");
		book.setNumOfCopies(1900);
		book.setBookName("Introduction to Algorithms");
		book.setPrice(20);
		
	    Book response = bookService.getBookById("9788120340077").get();
        assertEquals(book, response);
	}
	
	@Test
	public void getBookByAuthor() throws Exception {

		Book book = new Book();
		book.setIsbn("9788120340077");
		book.setAuthorName("Daniel Spielman");
		book.setNumOfCopies(1900);
		book.setBookName("Introduction to Algorithms");
		book.setPrice(20);
		List<Book> books = new ArrayList<Book>();
		books.add(book);
	    List<Book> response = bookService.getBookByAuthorName("Daniel Spielman").get();
        assertEquals(books, response);
	}
	
	@Test
	public void getAllBooks() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Book>> bookTypeReference = new TypeReference<List<Book>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/book.json");
		List<Book> books = mapper.readValue(inputStream, bookTypeReference);
		
		List<Book> response = bookService.getAllBooks();

        assertEquals(books, response);

    }
	

}
