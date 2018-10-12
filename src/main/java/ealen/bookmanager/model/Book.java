package ealen.bookmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-04.
 */
@ToString
@NoArgsConstructor
@Setter
@Getter
@Table(name = "t_book")
@Entity
public class Book extends BaseEntity {
    @Id
    private Integer id;
    private String name;
    private String introduction;
    private double price;
    private String author;
    private String isbn;
    private Integer number ;
    private String status = "F";

    public void setSafeProperty(String name, String author, String isbn, String status, int pageNo, int pageSize) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
        this.setPage(pageNo);
        this.setRows(pageSize);
    }

    public void setSafeProperty(String name, String author, String isbn, Integer number, double price, String introduction) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.number = number;
        this.price = price;
        this.introduction = introduction;
    }
}
