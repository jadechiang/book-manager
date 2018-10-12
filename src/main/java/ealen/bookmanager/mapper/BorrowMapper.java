package ealen.bookmanager.mapper;

import ealen.bookmanager.model.BorrowSet;
import ealen.bookmanager.util.MapperHelper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-05.
 */
public interface BorrowMapper extends MapperHelper<BorrowSet> {

    @Insert("INSERT INTO t_borrow(b_id,u_id) VALUES(#{b_id},#{u_id})")
    void addRecord(BorrowSet borrowSet);

    @Delete("DELETE FROM t_borrow WHERE b_id=#{bookId}")
    void removeRecord(@Param("bookId") Integer bookId);

    @Select("SELECT * FROM t_borrow WHERE u_id=#{userId}")
    List<BorrowSet> findByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM t_borrow WHERE b_id=#{bookId}")
    List<BorrowSet> findByBookId(@Param("bookId") Integer bookId);
}
