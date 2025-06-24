package com.project.pollingApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pollingApp.enitty.Poll;
import com.project.pollingApp.request.Vote;
import com.project.pollingApp.services.PollService;

@RestController
@RequestMapping("/api/poll")
@CrossOrigin(origins = "http://localhost:4200/")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    // Create a new poll
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }

    // Get all polls
    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }

    // Get poll by ID
    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        return pollService.getPollById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    // Cast a vote
    @PostMapping("/vote")
    public ResponseEntity<String> vote(@RequestBody Vote voteRequest) {
        if (voteRequest.getPollId() == null) {
            return ResponseEntity.badRequest().body("Poll ID must not be null");
        }

        try {
            pollService.vote(voteRequest.getPollId(), voteRequest.getOptionIndex());
            return ResponseEntity.ok("Vote recorded successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Poll not found");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An unexpected error occurred");
        }
    }
}
