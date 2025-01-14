package by.library.services;

import by.library.models.Book;
import by.library.models.Person;
import by.library.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> index(){
        return booksRepository.findAll();
    }

    public Book get(Integer id){
        return null;
    }

    @Transactional
    public void save(Book book){

    }

    @Transactional
    public void update(Integer id, Book book){

    }

    @Transactional
    public void delete(Integer id){

    }

    @Transactional
    public void release(Integer id){

    }

    @Transactional
    public void assign(Integer id, Person person){

    }

}
