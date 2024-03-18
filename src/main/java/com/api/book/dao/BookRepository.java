// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.api.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.book.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	public Book findById(int id);
	public List<Book> findAllByOrderByTitleAsc();
}
