package com.example.book.controller;

import com.example.book.entities.Author;
import com.example.book.entities.Book;
import com.example.book.repositories.AuthorRepository;
import com.example.book.repositories.BookRepository;
import com.example.book.specification.BookSpecification;
import com.example.book.utils.NullAwareBeanUtilsBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Autowired
    BookSpecification bookSpecification = new BookSpecification();
    @Autowired
    NullAwareBeanUtilsBean nullAwareBeanUtilsBean = new NullAwareBeanUtilsBean();

    @InjectMocks
    private BookController bookController;

    Author author = new Author("Test Name");

    @BeforeEach
    void setUp() {
        bookController = new BookController(bookRepository, authorRepository, nullAwareBeanUtilsBean, bookSpecification);
    }

    @Test
    void getAllBooks() {
        List<Book> mockList = new ArrayList<>();
        Mockito.when(bookRepository.findAll(Mockito.any())).thenReturn(mockList);

        bookController.getAllBooks("", 1, 1, "Author");

        Mockito.verify(bookRepository).findAll(Mockito.any());
    }

    @Test
    void getBookById__negative() {
        bookController.getBookById(1L);

        Mockito.verify(bookRepository).findById(Mockito.anyLong());
    }

    @Test
    void getBookById() {
        Optional<Book> mockBook = Optional.of(Mockito.mock(Book.class));

        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(mockBook);

        bookController.getBookById(1L);

        Mockito.verify(bookRepository).findById(Mockito.anyLong());
    }

    @Test
    void updateBookById__negative() {
        bookController.getBookById(1L);

        Mockito.verify(bookRepository).findById(Mockito.anyLong());
    }

}
