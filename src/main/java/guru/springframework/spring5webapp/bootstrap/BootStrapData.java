package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher rheinwerk = new Publisher();
        rheinwerk.setName("Rheinwerk");
        rheinwerk.setCity("Koblenz");
        rheinwerk.setState("RP");
        publisherRepository.save(rheinwerk);

        Publisher hanser = new Publisher();
        hanser.setName("Hanser");
        hanser.setCity("Hannover");
        hanser.setState("NS");
        publisherRepository.save(hanser);

        System.out.println("Publishers Count: "+ publisherRepository.count());

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(rheinwerk);
        rheinwerk.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(rheinwerk);

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB","349349987");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(hanser);
        hanser.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(hanser);

        System.out.println("Number of Books: "+ bookRepository.count());
        System.out.println("Rheinwerk Number of Books: "+ rheinwerk.getBooks().size());
        System.out.println("Hanser Number of Books: "+ hanser.getBooks().size());

    }
}
