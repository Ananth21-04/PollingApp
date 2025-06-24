import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Poll, NewPoll } from './poll.models';

@Injectable({
  providedIn: 'root'
})
export class PollService {

  private baseUrl = 'http://localhost:8080/api/poll';

  constructor(private http: HttpClient) {}

  // Create a new poll
  createPoll(poll: NewPoll): Observable<Poll> {
    return this.http.post<Poll>(this.baseUrl, poll);
  }

  // Fetch all polls
  getPolls(): Observable<Poll[]> {
    return this.http.get<Poll[]>(this.baseUrl);
  }

  // Submit a vote
  vote(pollId: number, optionIndex: number): Observable<void> {
    const url = `${this.baseUrl}/vote`;
    const body = { pollId, optionIndex }; // Matches backend VoteRequest
    return this.http.post<void>(url, body);
  }
}
