package ealen.bookmanager.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import ealen.bookmanager.model.Book;
import ealen.bookmanager.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static ealen.bookmanager.util.Constants.ERROR;
import static ealen.bookmanager.util.Constants.SUCCESS;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-05.
 */
@Controller
@RequestMapping("book")
public class BookController {
    @Resource
    private BookService bookService;

    @PostMapping("/list/json")
    public ModelAndView json(@RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "author", defaultValue = "") String author,
                             @RequestParam(value = "isbn", defaultValue = "") String isbn,
                             @RequestParam(value = "status", defaultValue = "") String status,
                             @RequestParam(value = "page", defaultValue = "1") int pageNo,
                             @RequestParam(value = "rows", defaultValue = "10") int pageSize) {
        ModelAndView mv = new ModelAndView("/book/json");
        Book book = new Book();
        book.setSafeProperty(name, author, isbn, status, pageNo, pageSize);
        mv.addObject("pageInfo", bookService.getAllBook(book));
        return mv;
    }

    @PostMapping("/names")
    public ModelAndView names() {
        ModelAndView mv = new ModelAndView("/book/names");
        mv.addObject("names", bookService.getAllNames());
        return mv;
    }

    @PostMapping("/authors")
    public ModelAndView authors() {
        ModelAndView mv = new ModelAndView("/book/authors");
        mv.addObject("authors", bookService.getAllAuthors());
        return mv;
    }

    @PostMapping("/addBook")
    public ModelAndView addBook(@RequestParam("bookName") String name,
                                @RequestParam("bookAuthor") String author,
                                @RequestParam("ISBN") String isbn,
                                @RequestParam("bookPrice") double price,
                                @RequestParam("bookNumber") Integer number,
                                @RequestParam("introduction") String introduction) {
        ModelAndView mv = new ModelAndView();
        Book b = new Book();
        b.setSafeProperty(name, author, isbn, number, price, introduction);
        String msg = bookService.addBook(b);
        if (msg.equals(SUCCESS)) {
            mv.setViewName("user/manager");
        } else {
            mv.addObject("msg", msg);
            mv.setViewName(ERROR);
        }
        return mv;
    }

    @PostMapping("/modify")
    public ModelAndView modify(@RequestParam("id") Integer id,
                               @RequestParam("modifyName") String name,
                               @RequestParam("modifyAuthor") String author,
                               @RequestParam("modifyPrice") double price,
                               @RequestParam("modifyISBN") String isbn,
                               @RequestParam("modifyNumber") Integer number,
                               @RequestParam("modifyIntro") String introduction) {
        ModelAndView v = new ModelAndView("user/manager");
        Book b = new Book();
        b.setId(id);
        b.setSafeProperty(name, author, isbn, number, price, introduction);
        bookService.modify(b);
        return v;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return new ModelAndView("/user/manager");
    }

}
