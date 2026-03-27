package ru.rockprogstar.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rockprogstar.springcourse.model.Book;
import ru.rockprogstar.springcourse.model.Person;
import ru.rockprogstar.springcourse.repositories.BookRepository;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) {
            return bookRepository.findAll(Sort.by("year"));
        } else {
            return bookRepository.findAll();
        }
    }
    
    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear) {
            return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        } else {
            return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        }
    }
    
    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }
    
    public List<Book> searchByTitle(String query) {
        return bookRepository.findByNameStartingWith(query);
    }
    
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }
    
    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = bookRepository.findById(id).get();
        
        updatedBook.setId(id);
        updatedBook.setPerson(bookToBeUpdated.getPerson());
        
        bookRepository.save(updatedBook);
    }
    
    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
    
    public Person getBookOwner(int id) {
        return bookRepository.findById(id).map(Book::getPerson).orElse(null);
    }
    
    @Transactional
    public void release(int id) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setPerson(null);
                    book.setTakenAt(null);
                }
        );
    }
    
    @Transactional
    public void assign(int id, Person selectedPerson) {
        bookRepository.findById(id).ifPresent(book -> {book.setPerson(selectedPerson); book.setTakenAt(new Date());});
    }
    
    public List<Book> getBooksByPersonId(int id) {
        return bookRepository.findAllByPersonId(id);
    }
    
}
