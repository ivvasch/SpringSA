package net.ivvasch.springsa.service;

import net.ivvasch.springsa.dao.UserDao;
import net.ivvasch.springsa.model.Role;
import net.ivvasch.springsa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // создаем пользователя путем поиска его в нашей базе данных
        User user = userDao.findByUserName(username);

        // создаем множество разрешений для пользователя
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();


        for (Role role : user.getRoleSet()) {
            // мы добавляем в разрешентя данного пользователя все роли, который у него хранятся в базе данных
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        // возвращаем фреймворк нового юзера в который передаем имя пользователя, пароль и разрешения
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
