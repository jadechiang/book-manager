package ealen.bookmanager.service;

import com.github.pagehelper.PageInfo;
import ealen.bookmanager.model.Book;

import java.util.Set;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-04.
 */
public interface IBookService {
    String borrowBook(Integer bookId, Integer userId);

    void giveBack(Integer bookId);

    Book getBookById(Integer bookId);

    PageInfo getAllBook(Book book);

    Set<String> getAllNames();

    Set<String> getAllAuthors();


    public void updateBook(Book book);

    public void deleteBook(int id);

}
