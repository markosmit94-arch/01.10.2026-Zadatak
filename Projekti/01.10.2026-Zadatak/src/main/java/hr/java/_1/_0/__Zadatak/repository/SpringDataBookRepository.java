package hr.java._1._0.__Zadatak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.java._1._0.__Zadatak.domain.Book;

import java.util.Optional;

public interface SpringDataBookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByIsbn(String isbn);
}
