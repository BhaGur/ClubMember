package com.example.SportsClubMember.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String memberName, city, birthDate, email, code;
	private int joinYear;
	
	@ManyToOne
	@JoinColumn(name = "statusId")
	private Status status;
	
	public Member() {
		super();
	}
	
	public Member( String memberName, String birthDate, int joinYear, Status status, String city, String code, String email ) {
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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
