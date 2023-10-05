package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.domain.Book;

/**
 * @author marcusheisters
 */
public interface BookService {

    Iterable<Book> findAll();

}
