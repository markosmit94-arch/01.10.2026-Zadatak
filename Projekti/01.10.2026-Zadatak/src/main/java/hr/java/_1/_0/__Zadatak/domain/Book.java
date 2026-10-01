package hr.java._1._0.__Zadatak.domain;

import hr.java._1._0.__Zadatak.dto.BookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    private int pages;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.getTitle();
        this.isbn = bookDTO.getIsbn();
        this.pages = bookDTO.getPages();
        this.price = bookDTO.getPrice();
        this.publisher = bookDTO.getPublisher();
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, isbn, pages, price, publisher);
    }
}
