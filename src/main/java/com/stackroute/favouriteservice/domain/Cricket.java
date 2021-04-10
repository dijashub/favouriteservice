package com.stackroute.favouriteservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.bytebuddy.implementation.bind.annotation.Super;

@Entity
@Table (name="cricket")
public class Cricket {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name="unique_id")
	private String unique_id;
	
	@Column(name ="match_date")
	private String match_date;

	@Column(name ="squad")
	private String squad;
	
	@Column(name ="team_1")
	private String team1;

	@Column(name ="team_2")
	private String team2;

	@Column(name ="user_id")
	private String userId;
	
	private String name;
	private String Date;
	

	@Column(name ="match_started")
	private String matchStarted;

	public Cricket(int id, String unique_id, String match_date, String squad, String team1, String team2,
			String matchStarted, String userId) {
		super();
		this.id = id;
		this.unique_id = unique_id;
		this.match_date = match_date;
		this.squad = squad;
		this.team1 = team1;
		this.team2 = team2;
		this.matchStarted = matchStarted;
		this.userId = userId;
	}
	
	public Cricket() {
		super();
	}
	
	
	public int getId() {
		return id;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}

	public String getMatch_date() {
		return match_date;
	}

	public void setMatch_date(String match_date) {
		this.match_date = match_date;
	}

	public String getSquad() {
		return squad;
	}

	public void setSquad(String squad) {
		this.squad = squad;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public String getMatchStarted() {
		return matchStarted;
	}

	public void setMatchStarted(String matchStarted) {
		this.matchStarted = matchStarted;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
