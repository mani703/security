package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.security.domain.Member;
import com.example.security.domain.Role;
import com.example.security.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Commit
@SpringBootTest
public class TestMemberRepository {
	
	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void test01() {
		Member member = new Member();
		
		member.setId("admin");
		member.setPassword("admin");
		member.setDname("admin 부서");
		member.setEnabled(true);
		member.setRole(Role.ROLE_ADMIN);
		memberRepository.save(member);
		
		member = new Member();
		member.setId("manager");
		member.setPassword("manager");
		member.setDname("manager 부서");
		member.setEnabled(true);
		member.setRole(Role.ROLE_MANAGER);
		memberRepository.save(member);
		
		member = new Member();
		member.setId("member");
		member.setPassword("member");
		member.setDname("member 부서");
		member.setEnabled(true);
		member.setRole(Role.ROLE_MEMBER);
		memberRepository.save(member);
		
	}
}
