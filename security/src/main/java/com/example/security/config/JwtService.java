package com.example.security.config;

public interface JwtService {

	String caretToken(String subject, long ttlMillis);
	
	String getSubject(String token);
	
	void isUsable(String jwt) throws Exception;
}
