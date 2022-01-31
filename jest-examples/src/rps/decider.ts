import { Player, RPSPlay } from "./rps";

export function decider(p1: Player, p2: Player): Player {
  if (
    (p1.play === RPSPlay.SCISSORS && p2.play === RPSPlay.ROCK) ||
    (p1.play === RPSPlay.PAPER && p2.play === RPSPlay.SCISSORS) ||
    (p1.play === RPSPlay.ROCK && p2.play === RPSPlay.PAPER)
  )
    return p2;
    
  return p1;
}
