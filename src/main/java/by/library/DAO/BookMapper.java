package by.library.DAO;

import by.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Component
public class BookMapper implements RowMapper<Book> {
/*    private final PersonDAO personDAO;

    @Autowired
    public BookMapper(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }*/

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("b_id"));
        book.setTitle(rs.getString("b_title"));
        book.setAuthor(rs.getString("b_author"));
        book.setYear(rs.getInt("b_year"));
       // book.setReader(personDAO.get(rs.getInt("b_reader")));
        return book;
    }
}
