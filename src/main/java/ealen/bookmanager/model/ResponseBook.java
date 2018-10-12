package ealen.bookmanager.model;

import lombok.*;

import java.io.Serializable;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-07.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBook implements Serializable {
    private Integer id;
    private String name;
    private String introduction;
    private double price;
    private String author;
    private String isbn;
    private Integer number;
    private String status;
}
