import React from "react";
import { ReactComponentDependency } from "./ReactComponentDependency";

export function ReactComponent(props: any) {
  return (
    <div>
      <div>I am a React Component</div>
      <ReactComponentDependency />
    </div>
  );
}
