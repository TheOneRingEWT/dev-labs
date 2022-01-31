it("should mock a function", () => {
  const mockFunction = jest.fn();
  console.log("mockFunction = ", mockFunction.mock);

  mockFunction(1, 2, 2);
  console.log("mockFunction = ", mockFunction.mock);
  expect(mockFunction).toHaveBeenCalledTimes(1);

  mockFunction("arg1", 2, 4);
  expect(mockFunction).toHaveBeenCalledTimes(2);
  expect(mockFunction).toHaveBeenCalledWith("arg1", 2, 4);

  mockFunction.mockReturnValue("someValue");

  const value = mockFunction();
  expect(value).toBe("someValue");
});

it("should mock a resolved Promise", async () => {
  const mockFunction = jest.fn();

  mockFunction.mockResolvedValue("I'm from a Promise");

  expect(await mockFunction()).toBe("I'm from a Promise");
});

it("should mock a rejected Promise", async () => {
  const mockFunction = jest.fn();

  mockFunction.mockRejectedValue("I'm rejected from a Promise");

  let rejected;

  try {
    await mockFunction();
  } catch (error) {
    rejected = error;
  }

  expect(rejected).toBe("I'm rejected from a Promise");
});

it("should return different values for different calls", async () => {
  const mockFunction = jest.fn();

  mockFunction
    .mockReturnValueOnce("firstValue")
    .mockReturnValueOnce("secondValue")
    .mockReturnValue("thirdValue");

  // Call once
  expect(mockFunction()).toBe("firstValue");

  // Call 2nd time
  expect(mockFunction()).toBe("secondValue");

  // Call 3rd time
  expect(mockFunction()).toBe("thirdValue");

  // Calls after 3rd time
  expect(mockFunction()).toBe("thirdValue"); // 4th Call
  expect(mockFunction()).toBe("thirdValue"); // 5th Call
  expect(mockFunction()).toBe("thirdValue"); // 6th Call

  expect(mockFunction).toHaveNthReturnedWith(2, "secondValue");
  expect(mockFunction).toHaveNthReturnedWith(5, "thirdValue");
});
