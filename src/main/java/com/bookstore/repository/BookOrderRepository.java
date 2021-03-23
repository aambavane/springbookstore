package com.bookstore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bookstore.model.BookOrder;

public interface BookOrderRepository extends CrudRepository<BookOrder, Integer>  
{  
	@Query(value = "select sum(AMOUNT) from Book_Order where PURCHASE_DATE >= CURDATE() - INTERVAL ?1 month", nativeQuery = true)
    int findPreviousMonthSales(int noOfMonths);
	
	@Query(value = "select sum(AMOUNT) from Book_Order where PURCHASE_DATE >= CURDATE() - INTERVAL ?1 year", nativeQuery = true)
    int findPreviousYearSales(int noOfYears);
	
	@Query(value = "select sum(AMOUNT) from Book_Order where PURCHASE_DATE >= CURDATE() - INTERVAL ?1 day", nativeQuery = true)
    int findPreviousDaySales(int noOfdays);
	
}  