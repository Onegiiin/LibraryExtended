package by.library.controllers;
import by.library.models.Book;
import by.library.models.Person;
import by.library.services.BooksService;
import by.library.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }
    
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){
        Book book = booksService.get(id);
        model.addAttribute("book", booksService.get(id));
        if (book.getReader() != null){
            model.addAttribute("people", peopleService.index());
            model.addAttribute("person", new Person());
        }
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
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") Integer id, Model model){
        model.addAttribute("book", booksService.get(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, @PathVariable("id")Integer id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") Integer id){
        booksService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") Integer id, @ModelAttribute("person") Person person){
        booksService.assign(id, person);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        booksService.delete(id);
        return "redirect:/books";
    }
}
