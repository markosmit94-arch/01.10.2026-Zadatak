package hr.java._1._0.__Zadatak.service;

import hr.java._1._0.__Zadatak.domain.Book;
import hr.java._1._0.__Zadatak.dto.BookDTO;
import hr.java._1._0.__Zadatak.repository.SpringDataBookRepository;
import hr.java._1._0.__Zadatak.repository.SpringDataPublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final SpringDataBookRepository bookRepository;
    private final SpringDataPublisherRepository publisherRepository;

    @Override
    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(BookDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).map(BookDTO::new);
    }

    @Override
    public Optional<BookDTO> save(BookDTO bookDTO) {
        return Optional.of(new BookDTO(bookRepository.save(new Book(bookDTO))));
    }

    @Override
    public Optional<BookDTO> update(String isbn, BookDTO bookDTO) {

        Optional<Book> bookDTOOptional = bookRepository.findByIsbn(isbn);

        if (bookDTOOptional.isPresent()) {
            Book bookToUpdate = bookDTOOptional.get();
            bookToUpdate.setTitle(bookDTO.getTitle());
            bookToUpdate.setIsbn(bookDTO.getIsbn());
            bookToUpdate.setPages(bookDTO.getPages());
            bookToUpdate.setPrice(bookDTO.getPrice());
            bookToUpdate.setPublisher(bookDTO.getPublisher());
            Book updatedBook = bookRepository.save(bookToUpdate);
            return Optional.of(new BookDTO(updatedBook));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByIsbn(String isbn) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);
        bookOptional.ifPresent(bookRepository::delete);
    }
}

