package com.myproject.myapi.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.myapi.model.Book;
import com.myproject.myapi.model.dtos.BookDto;
import com.myproject.myapi.service.BookService;

import jakarta.validation.Valid;

@RestController
public class BookController {
	
	private final BookService bookService;

	public BookController(BookService bookService) {
    this.bookService = bookService;
  }
	
	@PostMapping("/book")
	@Validated
	public ResponseEntity<Book> createBook(@Valid @RequestBody BookDto bookDto, BindingResult bindingResult) {
		Book newBook = bookDto.toBook();
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(newBook, HttpStatus.BAD_REQUEST);
		} else {
			Book createdBook = bookService.saveBook(bookDto.toBook());	
			return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/book/{id}")
	@Validated
	public ResponseEntity<Book> getBook(@PathVariable(value = "id") final int id) {
		Optional<Book> Book = bookService.getBook(id);
		if(Book.isPresent()) {
			return new ResponseEntity<>(Book.get(), HttpStatus.OK);
		} 
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/books")
	public ResponseEntity<Iterable<Book>> getBooks() {
		return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") final int id, @Valid @RequestBody BookDto bookDto) {
		Optional<Book> optionalBook = bookService.getBook(id);
		Book changeBook = bookDto.toBook();
		if(optionalBook.isPresent()) {
			Book updatedBook = optionalBook.get();
			updatedBook.setTitle(changeBook.getTitle());
			updatedBook.setAuthor(changeBook.getAuthor());
			bookService.saveBook(updatedBook);
			return new ResponseEntity<>(updatedBook, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable(value = "id") final int id) {
		Optional<Book> deleteBook = bookService.getBook(id);
		if (deleteBook.isPresent()) {
			bookService.deleteBook(id);
			return new ResponseEntity<>(deleteBook.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
}