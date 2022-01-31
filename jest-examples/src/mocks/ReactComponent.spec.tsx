/* eslint-disable testing-library/no-debugging-utils */
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import React from "react";
import * as ReactComponentModule from "./ReactComponent";
import { ReactComponent } from "./ReactComponent";
import { ReactComponentDependency } from "./ReactComponentDependency";

// jest.mock("./ReactComponentDependency");

beforeEach(() => {
  jest.restoreAllMocks();
});

it("should spy a React Component", () => {
  // Must comment out jest.mock for this test to pass

  const ReactComponentModuleSpy = jest.spyOn(
    ReactComponentModule,
    "ReactComponent"
  );

  render(<ReactComponent />);

  expect(screen.getByText("I am a React Component")).toBeVisible();
  expect(ReactComponentModuleSpy).toHaveBeenCalled();
  screen.debug();
});

it("should stub a ReactComponentDependency", () => {
  // Must uncomment jest.mock for this test to pass

  (ReactComponentDependency as jest.Mock).mockImplementation(() => {
    return <div>ReactComponentDependencyMock</div>;
  });

  render(<ReactComponent />);

  expect(screen.getByText("ReactComponentDependencyMock")).toBeVisible();
  screen.debug();
});

it("should fake a ReactComponentDependency", () => {
  // Must uncomment jest.mock for this test to pass

  (ReactComponentDependency as jest.Mock).mockImplementation(() => {
    return (
      <div>
        <button
          onClick={() => {
            console.log("I've been clicked");
          }}
        >
          ReactComponentDependencyMock
        </button>
      </div>
    );
  });
  render(<ReactComponent />);

  expect(screen.getByText("ReactComponentDependencyMock")).toBeVisible();
  screen.debug();
  userEvent.click(screen.getByText("ReactComponentDependencyMock"));
});
