package com.example.book.repositories;

import com.example.book.dto.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
