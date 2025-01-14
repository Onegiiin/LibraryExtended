package by.library.services;

import by.library.models.Book;
import by.library.models.Person;
import by.library.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return null;
    }

    public Optional<Person> get(String name, String middleName, String surname){
        return Optional.empty();
    }

    public List<Book> getBooks(Integer id){
        return null;
    }

    public void save(Person person){

    }

    public void update(Integer id, Person person){

    }

    public void delete(Integer id){

    }
}
