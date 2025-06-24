import { Component, OnInit } from '@angular/core';
import { PollService } from '../poll.service';
import { Poll, NewPoll } from '../poll.models';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-poll',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.css']
})
export class PollComponent implements OnInit {
  newPoll: NewPoll = {
    question: '',
    options: [
      { voteOption: '', voteCount: 0 },
      { voteOption: '', voteCount: 0 }
    ]
  };

  polls: Poll[] = [];

  constructor(private pollService: PollService) {}

  ngOnInit(): void {
    this.loadPolls();
  }

  loadPolls(): void {
    this.pollService.getPolls().subscribe({
      next: (response: Poll[]) => {
        this.polls = response;
      },
      error: (error) => {
        console.error('Error fetching polls:', error);
      }
    });
  }

  createPoll(): void {
    if (!this.newPoll.question.trim() || this.newPoll.options.some(opt => !opt.voteOption.trim())) {
      alert('Please enter a poll question and at least two valid options.');
      return;
    }

    this.pollService.createPoll(this.newPoll).subscribe({
      next: (createdPoll: Poll) => {
        this.polls.push(createdPoll);
        this.resetPoll();
      },
      error: (error) => {
        console.error("Error creating poll:", error);
      }
    });
  }

  resetPoll(): void {
    this.newPoll = {
      question: '',
      options: [
        { voteOption: '', voteCount: 0 },
        { voteOption: '', voteCount: 0 }
      ]
    };
  }

  vote(pollId: number, optionIndex: number): void {
    this.pollService.vote(pollId, optionIndex).subscribe({
      next: () => {
        const poll = this.polls.find(p => p.id === pollId);
        if (poll) {
          poll.options[optionIndex].voteCount++;
        }
      },
      error: (error) => {
        console.error("Error casting vote:", error);
      }
    });
  }

  addOption(): void {
    this.newPoll.options.push({ voteOption: '', voteCount: 0 });
  }

  removeOption(index: number): void {
    if (this.newPoll.options.length > 2) {
      this.newPoll.options.splice(index, 1);
    } else {
      alert("At least two options are required.");
    }
  }

  trackByIndex(index: number): number {
    return index;
  }
}
