package by.library.util;

import by.library.models.Person;
import by.library.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        peopleService.get(person.getName(), person.getMiddleName(), person.getSurname())
                .filter(p -> p.getId() != person.getId())
                .ifPresent(p -> errors.reject("initials", "Person with the same initials already exists"));
    }
}
