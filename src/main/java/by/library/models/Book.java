package by.library.models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Title can't be empty")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Author can't be empty")
    @Size(min = 5, max = 200, message = "Author must be between 5 and 200 characters")
    @Column(name = "author")
    private String author;

    @Range(min = 0, max = 2025, message = "Year must be in range from 0 to Current year")
    @Column(name = "year")
    private int year;

    @ManyToOne()
    @JoinColumn(name = "reader", referencedColumnName = "id")
    private Person reader;

    @JoinColumn(name = "takingTime")
    private Date takingTime;

    @Transient
    private boolean isExpired = false;

    public Book(int year, String title, int id, String author) {
        this.year = year;
        this.title = title;
        this.id = id;
        this.author = author;
    }

    public Book() {
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

    public Person getReader() {
        return reader;
    }

    public void setReader(Person reader) {
        this.reader = reader;
    }

    public Date getTakingTime() {
        return takingTime;
    }

    public void setTakingTime(Date takingTime) {
        this.takingTime = takingTime;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
