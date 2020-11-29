package com.example.book.repositories;

import com.example.book.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface AuthorRepository extends CrudRepository<Author, Long> {
    Page<Author> findAll(Pageable pageable);
}
