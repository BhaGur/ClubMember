package com.example.SportsClubMember.domain;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String memberName, city, email, code;
	private int joinYear;
	private LocalDate birthDate;
	
	@ManyToOne
	@JoinColumn(name = "statusId")
	private Status status;
	
	@ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("members")
	private Set<Game> games;
	
	public Member() {
		super();
	}
	
	public Member( String memberName, LocalDate birthDate, int joinYear, Status status, String city, String code, String email ) {
		super();
		this.memberName = memberName;
		this.birthDate = birthDate;
		this.joinYear = joinYear;
		this.status = status;
		this.city = city;
		this.code = code;
		this.email = email;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "membername")
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	@Column(name="city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="birthdate")
	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="joinyear")
	public int getJoinYear() {
		return joinYear;
	}

	public void setJoinYear(int joinYear) {
		this.joinYear = joinYear;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		if (this.status != null)
			return "Member [id=" + id + ", memberName=" + memberName + ", city=" + city + ", birthDate=" + birthDate
				+ ", email=" + email + ", code=" + code + ", joinYear=" + joinYear + ", Status =" + this.getStatus() + "]";
		else
			return "Member [id=" + id + ", memberName=" + memberName + ", city=" + city + ", birthDate=" + birthDate
					+ ", email=" + email + ", code=" + code + ", joinYear=" + joinYear + "]";
	}
	
	
}
