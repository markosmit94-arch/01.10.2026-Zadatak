package hr.java._1._0.__Zadatak.controller;

import hr.java._1._0.__Zadatak.dto.BookDTO;
import hr.java._1._0.__Zadatak.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> findAll() {
        return bookService.findAll();
    }

    @GetMapping("{isbn}")
    public BookDTO getByIsbn(@PathVariable final String isbn) {
        return bookService.findByIsbn(isbn)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book was not found by that ISBN")
                );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDTO save(@Valid @RequestBody final BookDTO command) {
        return bookService.save(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Book with this ISBN already exists"));
    }

    @PutMapping("{isbn}")
    public BookDTO update(@PathVariable String isbn, @Valid @RequestBody final BookDTO updatedBookDTO) {
        return bookService.update(isbn, updatedBookDTO)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book was not found by that ISBN")
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{isbn}")
    public void delete(@PathVariable String isbn) {
        bookService.deleteByIsbn(isbn);
    }
}
