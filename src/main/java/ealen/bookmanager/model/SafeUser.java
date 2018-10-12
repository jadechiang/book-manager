package ealen.bookmanager.model;

import lombok.*;

/**
 * #Author : EalenXie
 * #CreateTime : 2017-04-24 14:02
 */
@Getter
@Setter
public class SafeUser extends BaseEntity {
    private Integer id;
    private String username;
    private String gender;
    private String birth;
    private String email;
    private String telephone;
    private String address;
    private String role;
    private String deleted;
    public SafeUser() {
        this.deleted = "FA";
    }

    public SafeUser(Integer id, String username, String gender, String birth, String email, String telephone, String address, String role, String deleted) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.birth = birth;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.role = role;
        this.deleted = deleted;
    }
}
