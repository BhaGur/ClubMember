package com.example.SportsClubMember.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MatchTypeRepository extends CrudRepository<MatchType, Long>{
	List<MatchType> findByMatchType (String matchType);
}
