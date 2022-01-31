it("should spy a function in an object", () => {
  const anObject: any = {
    aFunc: function () {
      return 100;
    },
  };

  const spyOnAnObject = jest.spyOn(anObject, "aFunc");

  const value = anObject.aFunc();

  expect(spyOnAnObject).toHaveBeenCalledWith();
  expect(spyOnAnObject).toHaveBeenCalled();

  expect(value).toBe(100);
});

it("should mock a function in an object", () => {
  const aFuncMock = jest.fn().mockReturnValue(200);

  const anObject: any = {
    aFunc: aFuncMock,
  };

  const value = anObject.aFunc();

  expect(aFuncMock).toHaveBeenCalled();

  expect(value).toBe(200);
});
