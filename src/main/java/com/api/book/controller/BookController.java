// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.api.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService service;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = service.getBooks();
		/*
		if (books.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(books));
		*/
		if(books.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(books,HttpStatus.OK);
		}
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id) {
		Book bookById = service.getBookById(id);
		return (bookById != null ? new ResponseEntity<>(bookById, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/books/sorted")
	public ResponseEntity<List<Book>> getSortedBooks(){
		List<Book> sortedBooks = service.getSortedBooks();
		return (sortedBooks != null? new ResponseEntity<>(sortedBooks,HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book book2 = null;
		try {
			book2 = service.addBook(book);
			return new ResponseEntity<>(book2, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id) {
		Book book = null;
		try {
			book = service.deleteBook(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
		Book updatedBook = null;
		try {
			updatedBook = service.updateBook(id, book);
			return new ResponseEntity<>(updatedBook,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
