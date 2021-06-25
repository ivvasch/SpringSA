package net.ivvasch.springsa.dao;

import net.ivvasch.springsa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

}
