package com.example.book.specification;

import com.example.book.entities.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;
import java.util.Collection;

@Component
public class BookSpecification {
    public Specification<Book> isName(String name) {
            return (root, query, cb) -> {
                return cb.equal(root.get("name").as(String.class), name);
            };
    }

    public Specification<Book> isEdition(Integer edition) {
        return (root, query, cb) -> {
            return cb.equal(root.get("edition").as(Integer.class), edition);
        };
    }

    public Specification<Book> isYear(Integer year) {
        return (root, query, cb) -> {
            return cb.equal(root.get("publication_year").as(Integer.class), year);
        };
    }

    public Specification<Book> isAuthor(String givenAuthorName) {
        return (root, query, cb) -> {
            final Path<Collection<Book>> authors = root.join("authors");
            final Path<String> authorName = authors.get("name");
            return cb.equal(authorName, givenAuthorName);
        };
    }
}
