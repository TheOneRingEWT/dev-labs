import { rps, RPSPlay } from "./rps";
import { decider } from "./decider";

// jest.mock("./decider", () => ({
//   decider: jest.fn(),
// }));
// const deciderMock = decider as jest.Mock;

const p1 = { name: "Player 1", play: RPSPlay.ROCK };
const p2 = { name: "Player 2", play: RPSPlay.ROCK };

it("should result in p1 win when p1 plays Rock and p2 plays Scissors", () => {
  p1.play = RPSPlay.ROCK;
  p2.play = RPSPlay.SCISSORS;

  expect(rps(p1, p2).message).toBe(`${p1.name} wins with ${p1.play!}`);
});

it("should result in p2 win when p2 plays Rock and p1 plays Scissors", () => {
  p1.play = RPSPlay.SCISSORS;
  p2.play = RPSPlay.ROCK;

  expect(rps(p1, p2).message).toBe(`${p2.name} wins with ${p2.play!}`);
});
