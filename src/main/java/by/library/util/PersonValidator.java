package by.library.util;

import by.library.DAO.PersonDAO;
import by.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        personDAO.get(person.getName(), person.getMiddleName(), person.getSurname())
                .filter(p -> p.getId() != person.getId())
                .ifPresent(p -> errors.reject("initials", "Person with the same initials already exists"));
    }
}
