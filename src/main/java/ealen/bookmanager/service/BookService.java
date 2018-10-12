package ealen.bookmanager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ealen.bookmanager.mapper.BookMapper;
import ealen.bookmanager.mapper.BorrowMapper;
import ealen.bookmanager.model.Book;
import ealen.bookmanager.model.BorrowSet;
import ealen.bookmanager.model.ResponseBook;
import ealen.bookmanager.util.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ealen.bookmanager.util.Constants.*;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-05.
 */
@Service
public class BookService implements IBookService {
    private Book book;
    private BorrowSet borrowSet;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BorrowMapper borrowMapper;

    @Override
    public String borrowBook(Integer bookId, Integer userId) {        //借
        book = bookMapper.findById(bookId);
        if (book.getNumber() <= 0) return BORROW_FAIL_NL;
        book.setNumber(book.getNumber() - 1);
        book.setStatus("T");
        bookMapper.updateStatus(book);
        borrowSet = new BorrowSet(bookId, userId);
        borrowMapper.addRecord(borrowSet);
        return SUCCESS;
    }

    @Override
    public void giveBack(Integer bookId) {                          //还
        book = bookMapper.findById(bookId);
        book.setNumber(book.getNumber() + 1);
        book.setStatus("F");
        bookMapper.updateStatus(book);
        borrowMapper.removeRecord(bookId);
    }


    @Override
    public Book getBookById(Integer bookId) {
        return bookMapper.findById(bookId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageInfo getAllBook(Book book) {
        List<ResponseBook> responseBooks = new ArrayList<>();
        PageHelper.startPage(book.getPage(), book.getRows());
        Example example = new Example(Book.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(book.getName())) {
            criteria.andEqualTo("name", book.getName());
        }
        if (StringUtils.isNotEmpty(book.getStatus())) {
            criteria.andEqualTo("status", book.getStatus());
        }
        if (StringUtils.isNotEmpty(book.getAuthor())) {
            criteria.andEqualTo("author", book.getAuthor());
        }
        if (StringUtils.isNotEmpty(book.getIsbn())) {
            criteria.andEqualTo("isbn", book.getIsbn());
        }
        List<Book> books = bookMapper.selectByExample(example);
        responseBooks.addAll(books.stream().map(this::getResponse).collect(Collectors.toList()));
        PageInfo pageInfo = new PageInfo<>(books);
        pageInfo.setList(responseBooks);
        return pageInfo;
    }

    public void modify(Book book) {
        Book b = bookMapper.findById(book.getId());
        b.setSafeProperty(book.getName(), book.getAuthor(), book.getIsbn(), book.getNumber(), book.getPrice(), book.getIntroduction());
        bookMapper.updateBook(b);
    }

    private ResponseBook getResponse(Book book) {
        return new ResponseBook(book.getId(), book.getName(), book.getIntroduction(), book.getPrice(), book.getAuthor(), book.getIsbn(), book.getNumber(), book.getStatus());
    }

    @Override
    public Set<String> getAllNames() {
        return bookMapper.getAllNames();
    }

    @Override
    public Set<String> getAllAuthors() {
        return bookMapper.getAllAuthors();
    }

    public String addBook(Book book) {
        if (bookMapper.findByName(book.getName()) != null) {
            return ADD_FAIL_EXITS;
        }
        if (book.getNumber() <= 0) {
            return ADD_FAIL_NAN;
        }
        bookMapper.addBook(book);
        return SUCCESS;
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateStatus(book);
    }

    @Override
    public void deleteBook(int id) {
        bookMapper.deleteBook(id);
    }

}
