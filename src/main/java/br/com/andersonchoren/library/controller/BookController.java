package br.com.andersonchoren.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersonchoren.library.model.Book;
import br.com.andersonchoren.library.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Book book) {
        try {
            return new ResponseEntity<>(service.save(book), HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO Gerar um log de sistema com o erro
            return new ResponseEntity<>("Lamento, houve um erro inesperado em nosso sistema. Tenta mais tarde!!!",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> edit(@RequestBody Book book) {
        try {
            return new ResponseEntity<>(service.save(book), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Houve um erro inesperado em nossa aplicação, tente novamente mais tarde!!!",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> delete(@PathVariable("isbn") Long isbn) {
        var book = service.findOne(isbn);
        if (book.isPresent()) {
            service.delete(isbn);

            book = service.findOne(isbn);
            if (book.isEmpty()) {
                return new ResponseEntity<>("Livro removido com sucesso!!!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Não foi possível remover o livro!!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Livro não localizado!!!", HttpStatus.NOT_FOUND);
    }
}
