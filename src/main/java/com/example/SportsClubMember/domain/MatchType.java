package com.example.SportsClubMember.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MatchType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long typeId;
	private String matchType;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "matchType")
	private List<Game> games;
	
	public MatchType() {
	}
	
	public MatchType(String matchType) {
		super();
		this.matchType = matchType;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	
}
