package com.myproject.myapi.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.myapi.model.Book;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BookDto {
  @JsonProperty("title")
  @NotBlank(message = "The title field is empty.")
  @NotEmpty(message = "The title field must have at least one item.")
  @Size(min = 2, max = 100, message = "The length of the title must be between 2 and 100 characters.")
  private String title;

  @JsonProperty("author")
  @NotEmpty(message = "The author name field is empty.")
  @NotEmpty(message = "The author field must have at least one item.")
  @Size(min = 2, max = 100, message = "The length of the author name must be between 2 and 100 characters.")
  private String author;

  public Book toBook() {
    Book book = new Book();
    book.setTitle(title);
    book.setAuthor(author);
    return book;
  }
}