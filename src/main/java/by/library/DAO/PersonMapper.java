package by.library.DAO;

import by.library.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("p_id"));
        person.setName(rs.getString("p_name"));
        person.setMiddleName(rs.getString("p_middleName"));
        person.setSurname(rs.getString("p_surname"));
        person.setYearOfBirth(rs.getInt("p_yearOfBirth"));
        return person;
    }
}
