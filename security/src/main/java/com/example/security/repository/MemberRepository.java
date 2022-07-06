package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.security.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

}
