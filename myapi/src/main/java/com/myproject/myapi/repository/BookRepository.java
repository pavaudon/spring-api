package com.myproject.myapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myproject.myapi.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{

}
