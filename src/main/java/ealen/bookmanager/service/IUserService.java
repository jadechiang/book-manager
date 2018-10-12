package ealen.bookmanager.service;

import com.github.pagehelper.PageInfo;
import ealen.bookmanager.model.SafeUser;
import ealen.bookmanager.model.User;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-05.
 */
public interface IUserService {
    public PageInfo getBorrowBook(Integer userId);
    public User settingUser(User user);
    public PageInfo getUsers(SafeUser user);
}
