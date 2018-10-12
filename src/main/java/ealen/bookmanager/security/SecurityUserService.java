package ealen.bookmanager.security;

import ealen.bookmanager.mapper.UserMapper;
import ealen.bookmanager.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-06.
 */
@Service
public class SecurityUserService {
    @Resource
    private UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

}
