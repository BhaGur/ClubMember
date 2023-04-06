package com.example.SportsClubMember;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.SportsClubMember.web.MemberController;

@SpringBootTest
class SportsClubMemberApplicationTests {
	
	@Autowired
	private MemberController controller;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}
