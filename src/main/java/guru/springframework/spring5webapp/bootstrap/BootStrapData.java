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

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher literaPublisher = new Publisher("Editura Litera", "Strada Fabrica de Glucoza");
        publisherRepository.save(literaPublisher);

        System.out.println("Publishers nr = " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(literaPublisher);
        literaPublisher.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        // de fiecare data cand obiectul se modifica, terbuie sa faci SAVE
        publisherRepository.save(literaPublisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Dev Without EJB", "339432502");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(literaPublisher);
        literaPublisher.getBooks().add(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        // de fiecare data cand obiectul se modifica, terbuie sa faci SAVE
        publisherRepository.save(literaPublisher);

        System.out.println("Start in Bootstrap");
        System.out.println("Books nr = " + bookRepository.count());
        System.out.println("Publisher nr of books from repository = " + publisherRepository.count());
        System.out.println("Publisher nr of books = " + literaPublisher.getBooks().size());
    }
}
