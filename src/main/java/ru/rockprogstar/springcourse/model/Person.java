package ru.rockprogstar.springcourse.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String name;
    
    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int birthYear;
    
    public Person() {
    }
    
    public Person(int id, String name, int birthYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getBirthYear() {
        return birthYear;
    }
    
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
