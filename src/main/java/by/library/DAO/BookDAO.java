package by.library.DAO;

import by.library.model.Book;
import by.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book b LEFT JOIN Person p on p.p_id = b.b_reader", new BookMapper());
    }

    public Book get(Integer id){
        return jdbcTemplate.query("SELECT * FROM Book b LEFT JOIN Person p on p.p_id = b.b_reader WHERE b_id=?",  new BookMapper(), id)
                .stream().findFirst().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(b_title, b_author, b_year) VALUES(?,?,?)",
              book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(Integer id, Book book){
        jdbcTemplate.update("UPDATE Book SET b_title=?, b_author=?, b_year=? WHERE b_id=?",
                book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(Integer id){
        jdbcTemplate.update("DELETE FROM Book WHERE b_id=?", id);
    }

    public void release(Integer id){
        jdbcTemplate.update("UPDATE Book SET b_reader=null WHERE b_id=?", id);
    }

    public void assign(Integer id, Person person){
        jdbcTemplate.update("UPDATE Book SET b_reader=? WHERE b_id=?", person.getId(), id);
    }

}
