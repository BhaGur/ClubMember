package com.example.SportsClubMember;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.SportsClubMember.domain.AppUser;
import com.example.SportsClubMember.domain.AppUserRepository;
import com.example.SportsClubMember.domain.Game;
import com.example.SportsClubMember.domain.GameRepository;
import com.example.SportsClubMember.domain.MatchType;
import com.example.SportsClubMember.domain.Member;
import com.example.SportsClubMember.domain.MemberRepository;
import com.example.SportsClubMember.domain.Status;
import com.example.SportsClubMember.domain.StatusRepository;
import com.example.SportsClubMember.domain.MatchTypeRepository;

@SpringBootApplication
public class SportsClubMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsClubMemberApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner MemberList(MemberRepository repository, StatusRepository srepository, AppUserRepository urepository, MatchTypeRepository mrepository, GameRepository grepository ) {
		return (args) -> {
			/* srepository.save(new Status("Active-member"));
			 srepository.save(new Status("Retired-member"));
			 srepository.save(new Status("Board-member"));
			
			 mrepository.save(new MatchType("T-20 Div-1"));
			 mrepository.save(new MatchType("T-20 Div-3"));
			 mrepository.save(new MatchType("One-day"));

			
			 repository.save(new Member("Bhabishya Gurung", LocalDate.parse("1988-01-01"), 2018, srepository.findByStatus("Active-member").get(0), "Vantaa", "01600", "bhagur@email.com"));
			 repository.save(new Member("John Johnson", LocalDate.parse("1990-02-02"), 2019, srepository.findByStatus("Active-member").get(0), "Helsinki", "00400", "johnson@email.com"));
			 repository.save(new Member("Timo Makkarainen", LocalDate.parse("1995-12-23"), 2018, srepository.findByStatus("Active-member").get(0), "Helsinki", "00410", "timo.ma@email.com"));
			 repository.save(new Member("Matti Leijona", LocalDate.parse("1980-02-02"), 2010, srepository.findByStatus("Retired-member").get(0), "Espoo", "02620", "leijona@email.com"));

			 AppUser admin1 = new AppUser("admin1", "$2a$12$MSbb0GzZAWrAEUdNS9cns./k5Kwoy7l/ZQGZ4Eec2CSyzPk.1CS1K", "admin1@gmail.com", "ADMIN");
			 AppUser user1 = new AppUser("user1", "$2a$12$jSrN/nZSkkNXVyLrSIfVN.cg9FwJAhUSfLaSDJxmgGG6qifkcQSMK", "user@hh.com", "USER");
			 urepository.save(admin1);
			 urepository.save(user1);
			
			 grepository.save(new Game("HIFK", LocalDate.parse("2023-05-01"), mrepository.findByMatchType("T-20 Div-1").get(0)));
			 grepository.save(new Game("EmpireCC", LocalDate.parse("2023-05-08"), mrepository.findByMatchType("T-20 Div-1").get(0)));
			 grepository.save(new Game("TurkuCC", LocalDate.parse("2023-06-11"), mrepository.findByMatchType("One-day").get(0)));
			*/
		};
	}
}
