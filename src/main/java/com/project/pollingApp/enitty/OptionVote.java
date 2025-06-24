package com.project.pollingApp.enitty;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class OptionVote {
	private String option;
	private long voteCount = 0L;

}
