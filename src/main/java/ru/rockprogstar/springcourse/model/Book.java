package ru.rockprogstar.springcourse.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Book {
    private int id;
    
    @NotEmpty(message = "Name should not be empty")
    private String name;
    
    @NotEmpty(message = "Author should not be empty")
    private String author;
    
    @NotNull(message = "Year should not be empty")
    @Min(1000)
    @Max(9999)
    private int year;
    
    public Book(){
    
    }
    
    public Book(int id, Person person, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
}
