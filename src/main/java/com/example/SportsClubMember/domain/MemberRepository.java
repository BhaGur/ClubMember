package com.example.SportsClubMember.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MemberRepository extends CrudRepository<Member, Long>{
	List<Member> findAll();
	
	@Query("SELECT m FROM Member m WHERE " + "CONCAT(m.memberName, m.birthDate, m.joinYear, m.city, m.code)" +" LIKE %?1%")
	public List<Member> findByMemberName(String memberName);
	
}
