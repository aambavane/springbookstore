package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}
	
	public Optional<Book> getBookById(String isbn) {
		return bookRepository.findByIsbn(isbn);
	}
	
	public Optional<List<Book>> getBookByAuthorName(String author_name) {
		return bookRepository.findByAuthorName(author_name);
	}

	public int getBooksSoldByAuthor(String author_name) {
		return bookRepository.findBooksSoldByAuthor(author_name);
	}
	
	public Book addBook(Book newBook) {
		Optional<Book> bookExists = bookRepository.findByIsbn(newBook.getIsbn());
		if(bookExists.isPresent()) {
			newBook.setNumOfCopies(bookExists.get().getNumOfCopies()+newBook.getNumOfCopies());
		}
		return bookRepository.save(newBook);
        
	}
	
	public Book sellBook(Book newBook) throws Exception {
		Optional<Book> bookExists = bookRepository.findByIsbn(newBook.getIsbn());
		if(bookExists.isPresent()) {
			newBook.setNumOfCopies(bookExists.get().getNumOfCopies()-newBook.getNumOfCopies());
		}else {
			throw new Exception("Book not found");
		}
		return bookRepository.save(newBook);
        
	}
	
	public List<Book> addBooks(List<Book> newBooks) {
		List<Book> addedBooks = new ArrayList<Book>();
		for(Book newBook : newBooks) {
			addedBooks.add(addBook(newBook));
		}
		return addedBooks;
        
	}
	
}
