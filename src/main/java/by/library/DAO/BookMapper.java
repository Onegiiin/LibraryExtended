package by.library.DAO;

import by.library.model.Book;
import by.library.model.Person;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("b_id"));
        book.setTitle(rs.getString("b_title"));
        book.setAuthor(rs.getString("b_author"));
        book.setYear(rs.getInt("b_year"));


            rs.getInt("b_reader");
            Optional<Person> reader = rs.wasNull() || rs.getMetaData().getColumnCount() < 6
                    ? Optional.empty()
                    : Optional.of(new PersonMapper().mapRow(rs, rowNum));
            book.setReader(reader);
        return book;
    }
}
