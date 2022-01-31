import { decider } from "./decider";

export interface Player {
  name: string;
  play: RPSPlay | undefined;
}

export interface Result {
  message: string;
}

export enum RPSPlay {
  ROCK = "Rock",
  PAPER = "Paper",
  SCISSORS = "Scissors",
}

export function rps(p1: Player, p2: Player): Result {
  const winner = decider(p1, p2);
  return { message: `${winner.name} wins with ${winner.play!}` };
}
