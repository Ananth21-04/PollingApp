package com.project.pollingApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.pollingApp.enitty.Poll;
import com.project.pollingApp.enitty.VoteOptions;
import com.project.pollingApp.repo.PollRepository;

@Service
public class PollService {

    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    // Create a new poll
    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    // Get all polls
    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    // Get a poll by its ID
    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);
    }

    // Record a vote for a given poll and option index
    public void vote(Long pollId, int optionIndex) {
        if (pollId == null) {
            throw new IllegalArgumentException("Poll ID must not be null");
        }

        // Get the poll from the database
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found with ID: " + pollId));

        List<VoteOptions> options = poll.getOptions();

        // Validate the option index
        if (optionIndex < 0 || optionIndex >= options.size()) {
            throw new IllegalArgumentException("Invalid option index: " + optionIndex);
        }

        // Increment vote count
        VoteOptions selectedOption = options.get(optionIndex);
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);

        // Save updated poll
        pollRepository.save(poll);
    }
}
