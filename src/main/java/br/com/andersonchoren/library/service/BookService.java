package br.com.andersonchoren.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonchoren.library.model.Book;
import br.com.andersonchoren.library.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public void delete(Long isbn) {
        repository.deleteById(isbn);
    }

    public Optional<Book> findOne(Long isbn) {
        return repository.findById(isbn);
    }
}
