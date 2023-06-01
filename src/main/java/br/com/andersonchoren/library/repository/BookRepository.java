package br.com.andersonchoren.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.andersonchoren.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    public List<Book> findByAuthor(String name);
}
