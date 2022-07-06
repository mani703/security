package com.bit.security2.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bit.security2.domain.Role;
import com.bit.security2.domain.User;
import com.bit.security2.repo.RoleRepo;
import com.bit.security2.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService{

	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			log.info("User found in the database: {}", username);
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			user.getRoles().forEach(role -> { authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
	
	@Override
	public User saveUser(User user) {
		log.info("saving new user {} to the databse", user.getName());
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("saving new role {} to the databse", role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("adding role {} to user {}", roleName, username);
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String username) {
		log.info("fetcing user {}", username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("feching all users");
		return userRepo.findAll();
	}

	

}
