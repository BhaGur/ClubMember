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
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long statusId;
	private String status;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
	private List<Member> members;
	
	public Status() {	
	}
	
	public Status(String status) {
		super();
		this.status = status;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	@Override
	public String toString() {
		return "Status [statusId =" + statusId + ", status =" + status + "]";
	}
}
