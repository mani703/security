package com.example.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.security.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String>{

}
