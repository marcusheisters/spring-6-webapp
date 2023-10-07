package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Generates bootstrap data for the h2 database. CommandLineRunner indicates that the run method
 * will be run when its contained within a spring application.
 */
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
            PublisherRepository publisherRepository)
    {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    /**
     * Create bootstrap data when starting the application.
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // Generate and persist two authors
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");
        Author ericSaved = authorRepository.save(eric);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");
        Author rodSaved = authorRepository.save(rod);

        // Generate and persist two books
        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");
        Book dddSaved = bookRepository.save(ddd);

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");
        Book noEJBSaved = bookRepository.save(noEJB);

        // Add books to authors and vice versa
        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        // Generate and persist publishers
        Publisher godalming = new Publisher();
        godalming.setPublisherName("Godalming");
        godalming.setCity("London");
        godalming.setAddress("Picadilly 2");
        godalming.setZip("33236");
        Publisher godalmingSaved = publisherRepository.save(godalming);

        // Add publisher to books
        dddSaved.setPublisher(godalmingSaved);
        noEJBSaved.setPublisher(godalmingSaved);

        // Save the books
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        // Reporting
        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());


    }
}