package by.library.DAO;

import by.library.model.Book;
import by.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Person get(Integer id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE p_id=?",  new PersonMapper(), id)
                .stream().findFirst().orElse(null);
    }

    public Optional<Person> get(String name, String middleName, String surname){
        return jdbcTemplate.query("SELECT * FROM Person WHERE p_name=? AND \"p_middleName\"=? AND p_surname=?",
                        new PersonMapper(), name, middleName, surname)
                .stream().findFirst();
    }

    public List<Book> getBooks(Integer id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE b_reader=?",
                        new BookMapper(), id);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(\"p_name\", \"p_middleName\", \"p_surname\", \"p_yearOfBirth\") VALUES(?,?,?,?)",
                person.getName(), person.getMiddleName(), person.getSurname(), person.getYearOfBirth());
    }

    public void update(Integer id, Person person){
        jdbcTemplate.update("UPDATE Person SET \"p_name\"=?, \"p_middleName\"=?, \"p_surname\"=?, \"p_yearOfBirth\"=? WHERE p_id=?",
                person.getName(), person.getMiddleName(), person.getSurname(), person.getYearOfBirth(), id);
    }

    public void delete(Integer id){
        jdbcTemplate.update("DELETE FROM Person WHERE p_id=?", id);
    }
}
