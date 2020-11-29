package com.example.book.controller;

import com.example.book.entities.Author;
import com.example.book.entities.Book;
import com.example.book.repositories.AuthorRepository;
import com.example.book.repositories.BookRepository;
import com.example.book.specification.BookSpecification;
import com.example.book.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "book")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final NullAwareBeanUtilsBean nullAwareBeanUtils;
    private final BookSpecification bookSpecification;

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Integer edition,
                                                  @RequestParam(required = false) Integer year,
                                                  @RequestParam(required = false) String author){
            Specification<Book> specification = Specification.where(bookSpecification.isName(name))
                .and(bookSpecification.isEdition(edition))
                .and(bookSpecification.isYear(year))
                .and(bookSpecification.isAuthor(author));

            List<Book> books  = bookRepository.findAll(specification);
            return new ResponseEntity<>(books, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Optional<Book> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        List<Author> authors = book.getAuthors().stream().map(
                e -> {
                    return authorRepository.findById(e.getId()).get()  ;
                }
        ).collect(Collectors.toList());
        book.setAuthors(authors);

        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) throws InvocationTargetException, IllegalAccessException {
        Optional<Book> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            Book _book = bookData.get();
            nullAwareBeanUtils.copyProperties(_book, book);
            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
