package ealen.bookmanager.mapper;

import ealen.bookmanager.model.Book;
import ealen.bookmanager.util.MapperHelper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017/3/24 10:52
 */
public interface BookMapper extends MapperHelper<Book> {

    @Select("SELECT * FROM t_book where deleted = false")
    List<Book> getAllBooks();

    @Select("SELECT * FROM t_book where id=#{id}")
    Book findById(@Param("id") Integer id);

    @Select("SELECT * FROM t_book where name=#{name}")
    Book findByName(@Param("name") String name);

    @Update("UPDATE t_book SET number=#{number} ,status=#{status} WHERE name=#{name}")
    void updateStatus(Book book);

    @Select("SELECT name FROM t_book ")
    Set<String> getAllNames();

    @Select("SELECT author FROM t_book")
    Set<String> getAllAuthors();

    @Insert("INSERT INTO t_book(name,author,number,price,isbn,status,introduction,deleted) " +
            "VALUES(#{name},#{author},#{number},#{price},#{isbn},'F',#{introduction},'false')")
    void addBook(Book book);

    @Update("UPDATE t_book SET deleted = 'true' where id=#{id}")
    void deleteBook(@Param("id") int id);

    @Update("UPDATE t_book SET name=#{name},author=#{author},number=#{number},price=#{price},isbn=#{isbn},status=#{status},introduction=#{introduction} where id=#{id}")
    void updateBook(Book book);
}
