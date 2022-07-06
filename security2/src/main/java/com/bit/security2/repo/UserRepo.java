package com.bit.security2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit.security2.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
