package ealen.bookmanager.mapper;

import ealen.bookmanager.model.User;
import ealen.bookmanager.util.MapperHelper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 *#Author : EalenXie
 *#CreateTime : 2017/3/24 15:03
 */
public interface UserMapper extends MapperHelper<User> {

    @Select("SELECT * FROM t_user WHERE username=#{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM t_user WHERE id=#{id}")
    User findById(@Param("id") Integer id);

    @Update("UPDATE t_user SET address=#{address},gender=#{gender},telephone=#{telephone},birth=#{birth},email=#{email},signature=#{signature} where id=#{id}")
    void updateUser(User user);

    @Select("SELECT id,username,email,gender,birth,address,telephone,role FROM t_user where deleted='FA' ")
    List<User> getAllUsers();

    @Update("UPDATE t_user SET deleted ='TR' where id=#{id}")
    void deleteUser(User user);

    @Update("UPDATE t_user SET username=#{username}, gender=#{gender}, email=#{email},telephone=#{telephone},birth=#{birth},address=#{address} where id=#{id}")
    void modifyUser(User user);

    @Insert("INSERT INTO t_user(username,password,gender,email,telephone,birth,address,role,deleted) VALUES(#{username},#{password},#{gender},#{email},#{telephone},#{birth},#{address},#{role},#{deleted})")
    void addSafeUser(User user);
}
