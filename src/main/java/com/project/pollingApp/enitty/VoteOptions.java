package com.project.pollingApp.enitty;

import jakarta.persistence.Embeddable;

@Embeddable
public class VoteOptions {
    private String voteOption;
    private Long voteCount = 0L;

    public VoteOptions() {
    }

    public VoteOptions(String voteOption) {
        this.voteOption = voteOption;
        this.voteCount = 0L;
    }

    public String getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(String voteOption) {
        this.voteOption = voteOption;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}
