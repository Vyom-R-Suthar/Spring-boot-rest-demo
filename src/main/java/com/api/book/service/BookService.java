// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.api.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.book.entities.Book;

@Service
public class BookService {
	
	private static List<Book> list = new ArrayList<>();
	
	static {		
		list.add(new Book(1,"Java in depth","Dheeru Munduluru"));
		list.add(new Book(2,"Head first Java","ABC"));
		list.add(new Book(3,"Thinks in Java","XYZ"));
	}
	
	public List<Book> getBooks(){
		return list;
	}
	
	public Book getBookById(int id) {
		Book book = null;
		try {
		book = list.stream().filter(e -> e.getId() == id).findFirst().get();
		}catch(Exception e){
			e.printStackTrace();
		}
		return book;
	}
	
	public Book addBook(Book book) throws Exception {
		list.add(book);
		return book;
	}
	
	public Book deleteBook(int id) {
		Book book = getBookById(id);
		boolean remove = list.remove(book);
		return (remove == true? book:null);
	}

	public Book updateBook(int id, Book book) {
		Book bookToUpdate = getBookById(id);
		bookToUpdate.setTitle(book.getTitle());
		bookToUpdate.setAuthor(book.getAuthor());
		return bookToUpdate;
	}
}
