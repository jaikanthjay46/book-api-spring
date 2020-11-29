package com.example.book;

import com.example.book.entities.Author;
import com.example.book.repositories.AuthorRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@SpringBootApplication
public class BookApplication {

	@Autowired
    AuthorRepository authorRepository;

    @PostConstruct
    public void seedData() throws IOException {
        // Seed Database
        Reader in = new FileReader("src/main/resources/data.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String name = record.get("name");
            authorRepository.save(new Author(name));
        }
    }

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}
