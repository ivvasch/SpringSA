package net.ivvasch.springsa.service;

import net.ivvasch.springsa.dao.RoleDao;
import net.ivvasch.springsa.dao.UserDao;
import net.ivvasch.springsa.model.Role;
import net.ivvasch.springsa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(1L));
        user.setRoleSet(roles);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {

        return userDao.findByUserName(username);
    }
}
