package wuxicloud.ealen.test;

import ealen.bookmanager.Application;
import ealen.bookmanager.model.Book;
import ealen.bookmanager.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2017/3/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestDB {
    @Resource
    BookService bookService;
//
//    @Test
//    public void testInsertBook(){
//        Book book=new Book();
//        book.setBookId(1);
//        book.setBookName("Java编程思想");
//        book.setContent("Hello World!");
//        book.setPrice(78.5);
//        book.setStatus(false);
//        book.setBookNumber(10);
//        bookService.addBook(book);
//    }



}
