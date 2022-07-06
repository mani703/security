package com.bit.security2.service;

import java.util.List;

import com.bit.security2.domain.Role;
import com.bit.security2.domain.User;

public interface UserService {

	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User>getUsers();
}
