package com.example.security.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.security.domain.Member;

import lombok.Data;
import lombok.Getter;

@Data
public class SecurityUser extends User{

	private Member member;
	
	// security User 구현
	public SecurityUser(Member member) {
		super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
		
		this.member = member;
	}
}
