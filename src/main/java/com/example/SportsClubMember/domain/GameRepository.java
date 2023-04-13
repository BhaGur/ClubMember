package com.example.SportsClubMember.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long>{
	
	List<Game> findAll();
	
	@Query("SELECT g FROM Game g WHERE " + "CONCAT(g.opponent, g.gameDate, g.matchType)" +" LIKE %?1%")
	public List<Game> search(String opponent);
}
