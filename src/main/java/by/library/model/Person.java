package by.library.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Middle name can't be empty")
    @Size(min = 2, max = 40, message = "Middle name must be between 2 and 40 characters")
    private String middleName;

    @NotEmpty(message = "Surname can't be empty")
    @Size(min = 2, max = 30, message = "Surname must be between 2 and 30 characters")
    private String surname;


    @Range(min = 1900, max = 2025, message = "Year must be in range from 1900 to Current year")
    private int yearOfBirth;

    public Person(int id, String middleName, String name, String surname, int yearOfBirth) {
        this.id = id;
        this.middleName = middleName;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
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
}
