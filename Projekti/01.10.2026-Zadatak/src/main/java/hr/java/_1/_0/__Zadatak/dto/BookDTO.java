package hr.java._1._0.__Zadatak.dto;

import hr.java._1._0.__Zadatak.domain.Publisher;
import hr.java._1._0.__Zadatak.domain.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {

    @NotBlank(message = "Title must not be empty")
    private String title;

    @NotBlank(message = "ISBN must not be empty")
    private String isbn;

    @Positive(message = "Pages must be positive")
    private Integer pages;

    @PositiveOrZero(message = "Price must be positive or zero")
    private BigDecimal price;

    @NotNull(message = "Publisher must not be empty")
    private Publisher publisher;

    public BookDTO(Book book) {
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.pages = book.getPages();
        this.price = book.getPrice();
        this.publisher = book.getPublisher();
    }
}
