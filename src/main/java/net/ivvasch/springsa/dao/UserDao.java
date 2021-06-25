package net.ivvasch.springsa.dao;

import net.ivvasch.springsa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
