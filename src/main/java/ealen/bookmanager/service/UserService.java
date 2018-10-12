package ealen.bookmanager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ealen.bookmanager.mapper.BookMapper;
import ealen.bookmanager.mapper.BorrowMapper;
import ealen.bookmanager.mapper.UserMapper;
import ealen.bookmanager.model.Book;
import ealen.bookmanager.model.BorrowSet;
import ealen.bookmanager.model.SafeUser;
import ealen.bookmanager.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : EalenXie && #CreateTime : 2017-04-05.
 */
@SuppressWarnings("unchecked")
@Service
public class UserService implements IUserService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BorrowMapper borrowMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public PageInfo getBorrowBook(Integer userId) {
        List<Book> books = new ArrayList<>();
        List<BorrowSet> borrowSets = borrowMapper.findByUserId(userId);
        for (BorrowSet borrowSet : borrowSets) {
            Book book = bookMapper.findById(borrowSet.getB_id());
            books.add(book);
        }
        PageInfo pageInfo = new PageInfo(books);
        pageInfo.setList(books);
        return pageInfo;
    }

    @Override
    public User settingUser(User user) {
        User u = userMapper.findByUsername(user.getUsername());
        u.setSafeProperty(user.getGender(), user.getBirth(), user.getTelephone(), user.getEmail(), user.getAddress(), user.getSignature());
        userMapper.updateUser(u);
        return u;
    }

    @Override
    public PageInfo getUsers(SafeUser user) {
        List<SafeUser> safeUsers = new ArrayList<>();
        PageHelper.startPage(user.getPage(), user.getRows());
        List<User> users = userMapper.getAllUsers();
        for (User u : users) {
            SafeUser safeUser = getResponse(u);
            safeUsers.add(safeUser);
        }
        PageInfo pageInfo = new PageInfo(safeUsers);
        pageInfo.setList(safeUsers);
        return pageInfo;
    }

    private SafeUser getResponse(User u) {
        return new SafeUser(u.getId(), u.getUsername(), u.getGender(), u.getBirth(), u.getEmail(), u.getTelephone(), u.getAddress(), u.getRole(), u.getDeleted());
    }

    public String deleteUser(int id) {
        User u = userMapper.findById(id);
        if (!u.getRole().equals("admin")) {
            u.setDeleted("TR");
            userMapper.deleteUser(u);
            return "SUCCESS";
        } else {
            return "对不起,你没有这个权限";
        }
    }

    public void modifyUser(User user) {
        User u = userMapper.findById(user.getId());
        u.setUsername(user.getUsername());
        u.setSafeProperty(user.getGender(), user.getBirth(), user.getTelephone(), user.getEmail(), user.getAddress(), u.getSignature());
        userMapper.modifyUser(u);
    }

    public void addUser(User user) {
        //默认密码为123456
        user.setPassword("$2a$04$fQp52p9BJkZPjP2BE6wRCu4QQINt9IVZTQBzZMw64gV/.spQzMmUC");
        userMapper.addSafeUser(user);
    }

    public User findUserByName(String username) {
        return userMapper.findByUsername(username);
    }
}
