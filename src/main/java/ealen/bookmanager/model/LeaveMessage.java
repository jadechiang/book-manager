package ealen.bookmanager.model;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-05-10 09:37
 * 留言对象
 */
@Setter
@Getter
@Table(name = "t_message")
@Entity
@JSONType
public class LeaveMessage extends BaseEntity {
    @Id
    private Integer id;
    private String username;        //留言用户名
    private String content;         //留言信息
    private String reply;           //回复,允许为空
    private String createTime;      //创建时间
    private String deleted;         //删除状态
}
