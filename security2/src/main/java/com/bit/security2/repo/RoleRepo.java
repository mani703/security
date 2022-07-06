package com.bit.security2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.security2.domain.Role;
import com.bit.security2.domain.User;

public interface RoleRepo extends JpaRepository<Role, Long>{

	Role findByName(String name);

}
