package com.bookstore.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.model.Book;
import com.bookstore.services.BookService;

@RestController 
public class BookController extends Service{
	
	 Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	/**
	 * Welcome page 
	 * @return
	 */
	@RequestMapping("bookstore")
	public String index() {
		return "Welcome to my bookstore!";
	}
	
	/**
	 * API to get all books
	 * @return
	 */
	@GetMapping("book/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks(){
		logger.info(" API call for getAllBooks : ");
		List<Book> book = bookService.getAllBooks();
		if (book.size() > 0) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	/**
	 * Get the no of available copies for given book isbn
	 * @param isbn
	 * @return
	 */
	@GetMapping("book/getAvailability/{isbn}")
	public ResponseEntity<Object> getAvailability(@PathVariable String isbn){
		logger.info(" API call for getAvailability for --> "+isbn);
		if(isbn == null || isbn.isEmpty()) {
			return sendErrorMessage("No id passed to find availability", HttpStatus.CONFLICT);
		}
		
		Optional<Book> book = bookService.getBookById(isbn);
		if (book.isPresent()) {
			return new ResponseEntity<>(book.get().getNumOfCopies(), HttpStatus.OK);
		} else {
			Map<String, String> errorMap = new HashMap<String, String>(){{
                put("error", "book not found");
            }};
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * API to get Books by the unique id/isbn
	 * @param isbn
	 * @return
	 */
	
	@GetMapping("book/getBookById/{isbn}")
	public ResponseEntity<Object> getBookById(@PathVariable String isbn){
		logger.info(" API call for getBookById for --> "+isbn);
		if(isbn == null || isbn.isEmpty()) {
			return sendErrorMessage("No id passed to find book", HttpStatus.CONFLICT);
		}
		
		Optional<Book> book = bookService.getBookById(isbn);
		if (book.isPresent()) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			Map<String, String> errorMap = new HashMap<String, String>(){{
                put("error", "book not found");
            }};
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * API to get books by the author name 
	 * @param author_name
	 * @return
	 */
	
	@GetMapping("book/getBookByAuthor/{author_name}")
	public ResponseEntity<Object> getBookByAuthor(@PathVariable String author_name){
		logger.info(" API call for getBookByAuthor for --> "+author_name);
		if(author_name == null || author_name.isEmpty()) {
			return sendErrorMessage("no author passed", HttpStatus.CONFLICT);
		}
		
		Optional<List<Book>> book = bookService.getBookByAuthorName(author_name);
		if (book.isPresent() && book.get().size() > 0) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return sendErrorMessage("Book not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("book/getBooksSoldByAuthor/{author_name}")
	public ResponseEntity<Object> getBooksSoldByAuthor(@PathVariable String author_name){
		logger.info(" API call for getBooksSold for --> "+author_name);
		if(author_name == null || author_name.isEmpty()) {
			return sendErrorMessage("no author passed", HttpStatus.CONFLICT);
		}
		
		int booksSold = bookService.getBooksSoldByAuthor(author_name);
		return new ResponseEntity<>(booksSold, HttpStatus.OK);
		
	}
	
	/**
	 * Add a book. If the book exists then the no of copies will be incremented by the noOfCopies passed in newBook value
	 * @param newBook
	 * @return
	 */
	@PostMapping("addBook")
    public ResponseEntity<Object> addBook(@RequestBody Book newBook){
		logger.info("Add book with qty ");
        Book book = null;
        try {
            book = bookService.addBook(newBook);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (Exception e) {
        	return sendErrorMessage("Error adding book", HttpStatus.CONFLICT);
        }
    }
	
	
}