package service;

import domain.Book;

import java.util.Optional;
import java.util.Set;

public interface BookServiceInterface extends Service<Long, Book>{
    Optional<Book> addBook(Book b);
    Optional<Book> removeBook(Long id);
    Set<Book> getAllBooks();
    Optional<Book> updateBook(long id, Book b);
    Optional<Book> findBook(Long id);

}
