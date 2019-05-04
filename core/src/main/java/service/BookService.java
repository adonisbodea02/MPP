package service;

import domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.BookRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService implements BookServiceInterface{

    private static final Logger log = LoggerFactory.getLogger(
            BookService.class);

    @Autowired
    private BookRepository repo;

    @Override
    public Optional<Book> addBook(Book b) {
        log.trace("addBook book={}", b);
        Optional<Book> addedBook= Optional.of(repo.save(b));
        log.trace("method finished---");
        return addedBook;
    }

    @Override
    public Optional<Book> removeBook(Long id) {
        log.trace("removeBook id={}",id);
        Optional<Book> book = repo.findById(id);
        book.ifPresent(b -> repo.deleteById(id));
        log.trace("method finished----");
        return book;
    }

    @Override
    public Set<Book> getAllBooks() {
        log.trace("getAllBooks---");
        Set<Book> books = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toSet());
        log.trace("result={}",books);
        return books;
    }

    @Override
    @Transactional
    public Optional<Book> updateBook(long id, Book b) {
        log.trace("updateBook newBook={}", b);
        Optional<Book> dbBook=repo.findById(id);
        Book result=dbBook.orElse(b);
        result.setTitle(b.getTitle());
        result.setGenre(b.getGenre());
        result.setYear(b.getYear());
        log.trace("method finished----");
        return Optional.of(result);
    }

    @Override
    public Optional<Book> findBook(Long id) {
        log.trace("findBook id={}",id);
        Optional<Book> book = repo.findById(id);
        log.trace("method finished---");
        return book;
    }
}
