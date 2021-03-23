package com.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bookstore.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer>  
{  
	Optional<Book> findByIsbn(String isbn);
	Optional<List<Book>> findByAuthorName(String author_name);
	
	@Query(value = "select sum(o.QUANTITY) from Order_Detail o left join Book b on o.isbn = b.isbn where b.author_name = ?1",nativeQuery = true)
    int findBooksSoldByAuthor(String author_name);

}  