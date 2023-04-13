package com.example.SportsClubMember.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SportsClubMember.domain.Game;
import com.example.SportsClubMember.domain.GameRepository;
import com.example.SportsClubMember.domain.Member;
import com.example.SportsClubMember.domain.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private GameRepository grepository;
	
	public List<Member> listAll(String memberName){
		if (memberName != null) {
			return repository.findByMemberName(memberName);
		}
		return repository.findAll();
	}
	
	public List<Game> listGames(String opponent){
		if (opponent != null) {
			return grepository.search(opponent);	
		}
		return grepository.findAll();
	}
}
