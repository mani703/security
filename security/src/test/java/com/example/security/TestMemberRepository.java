package com.example.security;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.security.domain.Member;
import com.example.security.domain.Role;
import com.example.security.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@Commit
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMemberRepository {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void test01() {
		Member member = new Member();
		
		member.setId("admin");
		member.setPassword(passwordEncoder.encode("admin"));
		member.setDname("admin 부서");
		member.setEnabled(true);
		member.setRole(Role.ROLE_ADMIN);
		memberRepository.save(member);
		
		member = new Member();
		member.setId("manager");
		member.setPassword(passwordEncoder.encode("manager"));
		member.setDname("manager 부서");
		member.setEnabled(true);
		member.setRole(Role.ROLE_MANAGER);
		memberRepository.save(member);
		
		member = new Member();
		member.setId("member");
		member.setPassword(passwordEncoder.encode("member"));
		member.setDname("member 부서");
		member.setEnabled(true);
		member.setRole(Role.ROLE_MEMBER);
		memberRepository.save(member);
		
	}
}
