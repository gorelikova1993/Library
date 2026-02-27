package ru.rockprogstar.springcourse.dao;

import ru.rockprogstar.springcourse.model.Person;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonMapper implements RowMapper<Person> {
    
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        
        person.setId(resultSet.getInt("person_id"));
        person.setName(resultSet.getString("name"));
        person.setBirthYear(resultSet.getInt("birth_year"));
        return person;
    }
}
