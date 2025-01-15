package by.library.services;

import by.library.models.Book;
import by.library.models.Person;
import by.library.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> index(Integer page, Integer booksPerPage, Boolean sortByYear){
        if (page == null || booksPerPage == null || page < 0 || booksPerPage <= 0) {
            if (sortByYear != null && sortByYear) {
                return booksRepository.findAll(Sort.by("year"));
            }
            return booksRepository.findAll();
        }

        Pageable pageable = (sortByYear != null && sortByYear)
                ? PageRequest.of(page, booksPerPage, Sort.by("year"))
                : PageRequest.of(page, booksPerPage);

        return booksRepository.findAll(pageable).getContent();
    }

    public Book get(Integer id){
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> get(String title){
        return booksRepository.findByTitleContainingIgnoreCase(title);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(Integer id, Book book) {
        booksRepository.findById(id).ifPresent(bookToUpdate -> {
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setYear(book.getYear());
            booksRepository.save(bookToUpdate);
        });
    }

    @Transactional
    public void delete(Integer id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void release(Integer id){
        booksRepository.findById(id).ifPresent(existingBook -> existingBook.setReader(null));
    }

    @Transactional
    public void assign(Integer id, Person person){
        booksRepository.findById(id).ifPresent(existingBook -> {
            existingBook.setReader(person);
            existingBook.setTakingTime(new Date());
        });
    }

}
