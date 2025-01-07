package by.library.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Title can't be empty")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotEmpty(message = "Author can't be empty")
    @Size(min = 5, max = 200, message = "Author must be between 5 and 200 characters")
    private String author;

    @NotEmpty(message = "Year can't be empty")
    @Range(min = 0, max = 2025, message = "Year must be in range from 0 to Current year")
    private int year;

    public Book(int year, String title, int id, String author) {
        this.year = year;
        this.title = title;
        this.id = id;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
