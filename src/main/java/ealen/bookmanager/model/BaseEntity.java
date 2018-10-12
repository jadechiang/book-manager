
package ealen.bookmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 基础信息,添加页码属性,用于分页
 */
@Setter
@Getter
@NoArgsConstructor
public class BaseEntity implements Serializable {
    @Transient
    private Integer page = 1;
    @Transient
    private Integer rows = 10;

}
