package net.ivvasch.springsa.service;

import net.ivvasch.springsa.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
