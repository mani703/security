package com.example.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.domain.Member;
import com.example.security.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

/*
 * 로그인 할 때 쓰임
 * 
 */

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// database 접근 (mapper)
		Optional<Member> op = memberRepository.findById(username);
		
		// user가 존재하지 않을 시
		if(!op.isPresent()) {
			throw new UsernameNotFoundException("사용자 없음");
		}
		
		Member member = op.get();
		
		return new SecurityUser(member);
	}

}
