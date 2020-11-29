package com.example.book.controller;

import com.example.book.entities.Author;
import com.example.book.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorController authorController;

    Author author = new Author("Test Name");

    @Test
    void getAllAuthors() {
        Page<Author> page = Mockito.mock(Page.class);
        Mockito.when(page.getContent()).thenReturn(null);
        Mockito.when(authorRepository.findAll(Mockito.any())).thenReturn(page);

        authorController.getAllAuthors(0,10);

        Mockito.verify(authorRepository).findAll(Mockito.any());
    }

    @Test
    void insertAuthor() {
        authorController.insertAuthor(author);

        Mockito.verify(authorRepository).save(Mockito.any());
    }
}
