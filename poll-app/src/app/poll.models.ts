export interface VoteOption {
  voteOption: string;
  voteCount: number;
}

export interface Poll {
  id: number;
  question: string;
  options: VoteOption[];
}

export interface NewPoll {
  question: string;
  options: VoteOption[];
}
