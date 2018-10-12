package ealen.bookmanager.security;

import ealen.bookmanager.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-06.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService{
    @Resource
    private SecurityUserService securityUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=securityUserService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return new SecurityUser(user);
    }
}
