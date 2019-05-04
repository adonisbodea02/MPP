package ro.ubb.converter;

import domain.Book;
import ro.ubb.dto.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookConverter extends BaseConverter<Book, BookDTO> {

    @Override
    public Book convertDTOToModel(BookDTO dto) {
        Book book=Book.builder()
                .title(dto.getTitle())
                .genre(dto.getGenre())
                .year(dto.getYear())
                .build();
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDTO convertModelToDTO(Book book) {
        BookDTO bookDTO = BookDTO.builder()
                .title(book.getTitle())
                .genre(book.getGenre())
                .year(book.getYear())
                .build();
        bookDTO.setId(book.getId());
        return bookDTO;
    }
}
