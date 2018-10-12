package ealen.bookmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-04.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_borrow")
@Entity
public class BorrowSet {
    @Id
    @Column(name = "b_id")
    private Integer b_id;
    @Column(name = "u_id")
    private Integer u_id;
}
