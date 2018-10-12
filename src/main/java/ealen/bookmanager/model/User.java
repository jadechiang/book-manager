package ealen.bookmanager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017/3/24 09:38
 */

@ToString
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String gender;
    @Getter
    @Setter
    private String birth;
    @Getter
    @Setter
    private String telephone;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String signature;       //个人签名
    protected String role;
    @Getter
    @Setter
    private String deleted ;

    public User() {
        role = "ordinary";
        deleted="FA";
    }

    public String getRole() {
        return role;
    }
    /**
     * 设置安全属性
     */
    public void setSafeProperty(String gender, String birth, String telephone, String email, String address, String signature) {
        this.gender = gender;
        this.birth = birth;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.signature = signature;
    }
}
