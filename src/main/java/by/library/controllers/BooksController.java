package by.library.controllers;

import by.library.DAO.BookDAO;
import by.library.model.Book;
import by.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){
        model.addAttribute("book", bookDAO.get(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") Integer id, Model model){
        model.addAttribute("book", bookDAO.get(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(book);
        return "redirect:/books/" + book.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
