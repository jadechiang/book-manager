package ealen.bookmanager.mapper;

import com.github.pagehelper.Page;
import ealen.bookmanager.model.LeaveMessage;
import ealen.bookmanager.util.MapperHelper;
import org.apache.ibatis.annotations.Insert;

import java.util.Map;

/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-05-10 09:36
 */
public interface LeaveMessageMapper extends MapperHelper<LeaveMessage> {
    //通过参数得到所有的留言信息,返回页对象
    Page getAllMessage(Map<String, Object> param);

    //添加留言信息
    void addInformation(LeaveMessage leaveMessage);
}
