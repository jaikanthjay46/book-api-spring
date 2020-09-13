package com.example.book.controller;

import com.example.book.dto.Author;
import com.example.book.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping(value = "")
    public List<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        return authorRepository.findAll(paging).getContent();
    }

    // Debug Endpoint
    @PostMapping(value = "")
    public ResponseEntity<Author> insertAuthor(@RequestBody Author author) {
        Author insertedAuthor = authorRepository
                .save(author);
        return new ResponseEntity<>(insertedAuthor, HttpStatus.CREATED);
    }


}
