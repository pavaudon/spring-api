package com.myproject.myapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myproject.myapi.model.Book;
import com.myproject.myapi.repository.BookRepository;
import lombok.Data;

@Data
@Service
public class BookService {
	
	@Autowired
	private BookRepository BookRepository;

	public BookService (BookRepository bookRepository) {
    this.BookRepository = bookRepository;
  }

	public Optional<Book> getBook(final int id) {
		return BookRepository.findById(id);
	}
	
	public Iterable<Book> getBooks() {
		return BookRepository.findAll();
	}
	
	public void deleteBook(final int id) {
		BookRepository.deleteById(id);
	}
	
	public Book saveBook(Book Book) {
		Book savedBook = BookRepository.save(Book);
		return savedBook;
	}

}