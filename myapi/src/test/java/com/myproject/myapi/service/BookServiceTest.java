package com.myproject.myapi.service;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.myproject.myapi.model.Book;
import com.myproject.myapi.repository.BookRepository;

import java.util.List;

public class BookServiceTest {

  @Mock
  private BookRepository bookRepository;
  @InjectMocks
  private BookService bookService;
  @Before
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetBookById() {
    // Given
    int id = 1;
    Book book = new Book();
    book.setId(id);
    when(bookRepository.findById(id)).thenReturn(Optional.of(book));
    // When
    Optional<Book> result = bookService.getBook(id);
    // Then
    assertNotNull(result);
    assertEquals(id, result.get().getId());
  }

  @Test
  public void testDeleteBook() {
    // Given
    int id = 1;
    // When
    bookService.deleteBook(id);
    // Then
    verify(bookRepository, times(1)).deleteById(id);
  }

  @Test
  public void testGetBooks() {
    // Given
    List<Book> books = new ArrayList<>();
    Book book1 = new Book();
    book1.setId(1);
    book1.setTitle("Titre 1");
    book1.setAuthor("Auteur 1");
    books.add(book1);
    Book book2 = new Book();
    book2.setId(2);
    book2.setTitle("Titre 2");
    book2.setAuthor("Auteur 2");
    books.add(book2);

    when(bookRepository.findAll()).thenReturn(books);

    // When
    Iterable<Book> result = bookService.getBooks();

    // Then
    assertNotNull(result);

    List<Book> resultList = new ArrayList<>();
    result.forEach(resultList::add);

    assertEquals(books.size(), resultList.size());
    // Comparaison des livres individuels dans la liste retournée et la liste initiale
    for (int i = 0; i < books.size(); i++) {
        assertEquals(books.get(i).getId(), resultList.get(i).getId());
        assertEquals(books.get(i).getTitle(), resultList.get(i).getTitle());
        assertEquals(books.get(i).getAuthor(), resultList.get(i).getAuthor());
        // Ajoute d'autres assertions selon les attributs que tu souhaites vérifier
    }
  }

  @Test
  public void testSaveBook() {
    // Given
    Book book = new Book();
    // When
    when(bookRepository.save(book)).thenReturn(book);
    Book savedBook = bookService.saveBook(book);
    // Then
    assertNotNull(savedBook);
    assertEquals(book.getId(), savedBook.getId());
    assertEquals(book.getTitle(), savedBook.getTitle());
    assertEquals(book.getAuthor(), savedBook.getAuthor());
  }
}
