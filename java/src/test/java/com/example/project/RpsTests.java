/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RpsTests {

	@Test
	@DisplayName("When P1 plays Rock and P2 plays Scissors, then P1 wins")
	void whenP1RockAndP2Scissors_thenReturnP1Wins() {
		Rps subject = new Rps();
		String expected = "Rock beats scissors. P1 Wins!";
		assertEquals(expected, subject.play(), "P1 Wins!");
	}
}
