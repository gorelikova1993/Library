package ru.rockprogstar.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rockprogstar.springcourse.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByPersonId(int id);
    
    List<Book> findByNameStartingWith(String name);
}
