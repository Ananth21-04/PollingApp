package com.project.pollingApp.enitty;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="poll")
@Data
public class Poll {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String question;
	
	@ElementCollection
	List<VoteOptions> options = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<VoteOptions> getOptions() {
		return options;
	}

	public void setOptions(List<VoteOptions> options) {
		this.options = options;
	}

	public List<Long> getVotes() {
		return votes;
	}

	public void setVotes(List<Long> votes) {
		this.votes = votes;
	}

	@ElementCollection
	List<Long> votes = new ArrayList<>();
	

}
