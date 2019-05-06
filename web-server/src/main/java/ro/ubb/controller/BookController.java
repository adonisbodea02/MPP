package ro.ubb.controller;

import ro.ubb.converter.BookConverter;
import domain.Book;
import ro.ubb.dto.BookDTO;
import ro.ubb.dto.BooksDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BookServiceInterface;

import java.util.Optional;
import java.util.Set;

@RestController
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookServiceInterface bookService;

    @Autowired
    private BookConverter bookConverter;

    @RequestMapping(value="/books",method = RequestMethod.GET)
    Set<BookDTO> getAllBooks(){
        log.trace("getAllBooks --- method entered");

        Set<Book> books = bookService.getAllBooks();
        Set<BookDTO> dtos = bookConverter.convertModelsToDTOs(books);
        BooksDTO result = new BooksDTO(dtos);

        log.trace("getAllBooks: result={}", result);

        return dtos;
    }

    @RequestMapping(value="/books",method = RequestMethod.POST)
    BookDTO saveBook(@RequestBody BookDTO bookDTO){
        log.trace("saveMovie: ro.ubb.dto={}", bookDTO);

        Optional<Book> saved = bookService.addBook(bookConverter.convertDTOToModel(bookDTO));
        BookDTO result = bookConverter.convertModelToDTO(saved.get());

        log.trace("saveBook: result={}", result);

        return result;
    }

    @RequestMapping(value="/books/{id}",method = RequestMethod.PUT)
    BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        log.trace("updateBook: id={} ro.ubb.dto={}", id, bookDTO);
        Optional<Book> updated = bookService.updateBook(id, bookConverter.convertDTOToModel(bookDTO));
        BookDTO result = bookConverter.convertModelToDTO(updated.get());

        log.trace("updateBook: result={}",result);

        return result;
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeBook(@PathVariable Long id) {
        log.trace("removeBook: id={}", id);
        boolean deleted = false;
        Optional<Book> book = bookService.removeBook(id);
        if (book.isPresent())
            deleted = true;
        log.trace("removeBook --- method finished");
        if (deleted)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/books/{id}",method = RequestMethod.GET)
    BookDTO findOneBook(@PathVariable Long id){
        log.trace("findOneBook: id={}",id);
        boolean found = false;
        BookDTO result = null;
        Optional<Book> book = bookService.findBook(id);
        if (book.isPresent())
            found=true;
        if (found)
            result = bookConverter.convertModelToDTO(book.get());

        log.trace("findOneBook: ro.ubb.dto={}", result);
        return result;
    }
}
