package by.library.services;

import by.library.models.Book;
import by.library.models.Person;
import by.library.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> index(){
        return peopleRepository.findAll();
    }

    public Person get(Integer id){
        Person person = peopleRepository.findById(id).orElse(null);
        if (person != null) {
            Hibernate.initialize(person.getBookList());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, -10); // Отнимаем 10 дней
            Date expirationDate = calendar.getTime();

            for (Book book : person.getBookList()) {
                book.setExpired(book.getTakingTime().before(expirationDate));
            }
        }
        return person;
    }

    public Optional<Person> get(String name, String middleName, String surname){
        return peopleRepository.findByNameAndMiddleNameAndSurname(name, middleName, surname);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Integer id, Person person){
        peopleRepository.findById(id).ifPresent(personToUpdate -> {
            personToUpdate.setName(person.getName());
            personToUpdate.setMiddleName(person.getMiddleName());
            personToUpdate.setSurname(person.getSurname());
            personToUpdate.setYearOfBirth(person.getYearOfBirth());
            peopleRepository.save(personToUpdate);
                }
        );
    }

    @Transactional
    public void delete(Integer id){
        peopleRepository.deleteById(id);
    }
}
