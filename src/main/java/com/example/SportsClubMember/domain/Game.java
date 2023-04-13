package com.example.SportsClubMember.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long gameid;
	private String opponent;
	private LocalDate gameDate;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "game_member", joinColumns = { @JoinColumn(name = "gameid") }, inverseJoinColumns = { @JoinColumn (name = "id") })
	private Set<Member> members = new HashSet<>();

	@ManyToOne
	@JoinColumn(name="typeId")
	private MatchType matchType;
		
	public Game() {
		super();
	}
	
	public Game (String opponent, LocalDate gameDate, MatchType matchType) {
		super();
		this.opponent = opponent;
		this.gameDate = gameDate;
		this.matchType = matchType;
	}

	public long getGameid() {
		return gameid;
	}

	public void setGameid(long gameid) {
		this.gameid = gameid;
	}
	
	@Column(name = "opponent")
	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
	
	@Column(name = "gamedate")
	public LocalDate getGameDate() {
		return gameDate;
	}

	public void setGameDate(LocalDate gameDate) {
		this.gameDate = gameDate;
	}
	
	public MatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(MatchType matchType) {
		this.matchType = matchType;
	}

	public Set<Member> getMembers() {
		return this.members;
	}
	
	public void setMembers(Set<Member> members) {
		this.members = members;
	}
	
	public boolean hasMember(Member member) {
		for (Member gameMember: getMembers()) {
			if (gameMember.getId() == member.getId()) {
				return true;
			}
		}
		return false;
	}
}
