package hr.java._1._0.__Zadatak.service;

import hr.java._1._0.__Zadatak.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDTO> findAll();

    Optional<BookDTO> findByIsbn(String isbn);

    Optional<BookDTO> save(BookDTO bookDTO);

    Optional<BookDTO> update(String isbn, BookDTO updatedBookDTO);

    void deleteByIsbn(String isbn);
}
