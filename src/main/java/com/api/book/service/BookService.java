// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.api.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Book;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;
	
	public List<Book> getBooks(){
		List<Book> books = repository.findAll();
		return books;
	}
	
	public Book getBookById(int id) {
		Book book = repository.findById(id);
		return book;
	}
	
	public Book addBook(Book book) throws Exception {
		Book addedBook = repository.save(book);
		return addedBook;
	}
	
	public Book deleteBook(int id) {
		Book book = repository.findById(id);
		repository.deleteById(id);
		return book;
	}

	public Book updateBook(int id, Book book) {
		book.setId(id);
		Book updatedBook = repository.save(book);
		return updatedBook;
	}

	public List<Book> getSortedBooks() {
		List<Book> sortedBooks = repository.findAllByOrderByTitleAsc();
		return sortedBooks;
	}
}
