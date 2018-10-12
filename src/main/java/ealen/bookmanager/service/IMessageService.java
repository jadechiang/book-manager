package ealen.bookmanager.service;

import ealen.bookmanager.model.LeaveMessage;
import ealen.bookmanager.util.PageBean;
import ealen.bookmanager.util.PageParam;


/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-05-10 09:56
 */
public interface IMessageService {

    PageBean getAllMessage(PageParam param);

    void addInformation(LeaveMessage leaveMessage);

}
