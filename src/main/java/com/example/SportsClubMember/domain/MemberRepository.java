package com.example.SportsClubMember.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface MemberRepository extends CrudRepository<Member, Long>{
	List<Member> findByMemberName(@Param("memberName") String memberName);

}
