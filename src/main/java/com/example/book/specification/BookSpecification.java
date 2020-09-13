package com.example.book.specification;

import com.example.book.dto.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;
import java.util.Collection;

@Component
public class BookSpecification {
    public static Specification<Book> isName(String name) {
            return (root, query, cb) -> {
                return cb.equal(root.get("name").as(String.class), name);
            };
    }

    public static Specification<Book> isEdition(int edition) {
        return (root, query, cb) -> {
            return cb.equal(root.get("edition").as(Integer.class), edition);
        };
    }

    public static Specification<Book> isYear(int year) {
        return (root, query, cb) -> {
            return cb.equal(root.get("publication_year").as(Integer.class), year);
        };
    }

    public static Specification<Book> isAuthor(String givenAuthorName) {
        return (root, query, cb) -> {
            final Path<Collection<Book>> authors = root.get("authors");
            final Path<String> authorName = authors.get("name");
            return cb.equal(authorName, givenAuthorName);
        };
    }
}
