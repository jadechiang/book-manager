package ealen.bookmanager.service;

import com.github.pagehelper.Page;
import ealen.bookmanager.mapper.LeaveMessageMapper;
import ealen.bookmanager.model.LeaveMessage;
import ealen.bookmanager.util.PageBean;
import ealen.bookmanager.util.PageParam;
import ealen.bookmanager.util.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-05-10 09:57
 */
@Service
public class MessageService implements IMessageService {
    @Resource
    private LeaveMessageMapper leaveMessageMapper;

    @Override
    public PageBean getAllMessage(PageParam param) {
        PageUtils.paging(param);
        Map<String, Object> p = PageUtils.getCommonParam();
        Page page = leaveMessageMapper.getAllMessage(p);
        return PageUtils.getPageBean(param, page);
    }

    @Override
    public void addInformation(LeaveMessage leaveMessage) {
        if (leaveMessage.getContent().isEmpty()) leaveMessage.setDeleted("true");
        leaveMessageMapper.addInformation(leaveMessage);
    }
}
