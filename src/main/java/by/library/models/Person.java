package by.library.models;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Middle name can't be empty")
    @Size(min = 2, max = 40, message = "Middle name must be between 2 and 40 characters")
    @Column(name = "middleName")
    private String middleName;

    @NotEmpty(message = "Surname can't be empty")
    @Size(min = 2, max = 30, message = "Surname must be between 2 and 30 characters")
    @Column(name = "surname")
    private String surname;

    @Range(min = 1900, max = 2025, message = "Year must be in range from 1900 to Current year")
    @Column(name = "yearOfBirth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "reader")
    private List<Book> bookList;

    public Person(int id, String middleName, String name, String surname, int yearOfBirth, List<Book> bookList) {
        this.id = id;
        this.middleName = middleName;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.bookList = bookList;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
