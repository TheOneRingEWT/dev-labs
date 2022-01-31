import * as MockModules from "./mockModules";
import { theRealThing, theRealThingToo } from "./mockModules";

// jest.mock("./mockModules");

jest.mock("./mockModules", () => ({
  ...jest.requireActual("./mockModules"),
  theRealThing: jest.fn(),
}));

console.log("module object = ", MockModules);
console.log(theRealThing());
console.log(MockModules.theRealThingToo());

it("should use a mocked function from the module", async () => {
  (theRealThing as jest.Mock).mockReturnValue("fakeRealThing");

  expect(theRealThing()).toBe("fakeRealThing");
  expect(theRealThing).toHaveBeenCalledTimes(1);

  // Uses actual because it was not overridden in the mocked module
  expect(theRealThingToo()).toBe("I'm the RealThing, Too!");
});
