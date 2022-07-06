package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * 어떤 권한으로 어떤 화면을 보여주고 
 * 어떻게 설정할 것인지 정의하는 부분
 * 
 */

@Configuration // jwt 토큰을 위함
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		
		security.authorizeRequests().antMatchers("/").permitAll(); // 누구든 접근 가능한 경로
		security.authorizeRequests().antMatchers("/main").authenticated(); // 스프링 시큐리티에 의해 로그인 되어있는 사람만 접근
		security.authorizeRequests().antMatchers("/member/**").authenticated(); // 어떤 권한이든 로그인 되어있는 사람만 접근
		security.authorizeRequests().antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN"); // ROLE_MANAGER, ROLE_ADMIN 권한 유저만 접근
		security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN"); // ROLE_ADMIN 권한 유저만 접근
		
		security.csrf().disable();
		
		security.formLogin().loginPage("/login").defaultSuccessUrl("/main", true); // 로그인 페이지를 성공했을 때 넘겨주는 페이지
		security.formLogin().loginProcessingUrl("/loginAction").defaultSuccessUrl("/main", true); // 로그인 액션 프로세스 반드시 POST
		security.exceptionHandling().accessDeniedPage("/accessDenied"); // 권한 없을때 접근하는 경우
		security.logout().logoutUrl("/logout").logoutSuccessUrl("/"); // 로그아웃 URL과 넘겨주는 페이지
		security.userDetailsService(userDetailService);
	}
	
	// password 암호화
	@Bean
	public PasswordEncoder passEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
