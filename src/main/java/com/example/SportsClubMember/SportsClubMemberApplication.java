package com.example.SportsClubMember;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.SportsClubMember.domain.AppUser;
import com.example.SportsClubMember.domain.AppUserRepository;
import com.example.SportsClubMember.domain.Member;
import com.example.SportsClubMember.domain.MemberRepository;
import com.example.SportsClubMember.domain.Status;
import com.example.SportsClubMember.domain.StatusRepository;

@SpringBootApplication
public class SportsClubMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsClubMemberApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner MemberList(MemberRepository repository, StatusRepository srepository, AppUserRepository urepository) {
		return (args) -> {
			srepository.save(new Status("Active-member"));
			srepository.save(new Status("Retired-member"));
			srepository.save(new Status("Board-member"));
			
			repository.save(new Member("Bhabishya Gurung", "01-01-2020", 2018, srepository.findByStatus("Active-member").get(0), "Vantaa", "01600", "bhagur@email.com"));
			
			AppUser admin1 = new AppUser("admin1", "$2a$12$MSbb0GzZAWrAEUdNS9cns./k5Kwoy7l/ZQGZ4Eec2CSyzPk.1CS1K", "admin1@gmail.com", "ADMIN");
			AppUser user = new AppUser("user", "$2a$12$jSrN/nZSkkNXVyLrSIfVN.cg9FwJAhUSfLaSDJxmgGG6qifkcQSMK", "user@h.com", "USER");
			urepository.save(admin1);
			urepository.save(user);
		};
	}
}
