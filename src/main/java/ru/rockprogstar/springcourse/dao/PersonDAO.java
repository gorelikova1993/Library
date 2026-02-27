package ru.rockprogstar.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rockprogstar.springcourse.model.Person;
import ru.rockprogstar.springcourse.model.Book;


import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    
    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name = ?", new Object[]{name},
                new PersonMapper()).stream().findAny();
    }
    
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, birth_year) VALUES(?,?)", person.getName(),
                person.getBirthYear());
    }
    
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name = ?, birth_year = ? WHERE id = ?", updatedPerson.getName(),
                updatedPerson.getBirthYear(), id);
    }
    
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id = ?", id);
    }
    
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book where person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
