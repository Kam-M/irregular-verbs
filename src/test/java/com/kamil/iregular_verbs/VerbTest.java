package com.kamil.iregular_verbs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VerbTest {
	
	Verb exampleVerb;
	
	@BeforeEach
	void setUp() {
		this.exampleVerb = new Verb("brać", "take", "took", "taken", 0);
	}

	@Test
	void testToString() {
		// given
		String expectedToString = "take -- took -- taken || brać";
		// when
		String verbToString = exampleVerb.toString();
		// then
		assertEquals(expectedToString, verbToString);
	}

	@Test
	void testToStringByTranslation() {
		// given
		String expectedToString = "brać || take -- took -- taken";
		// when
		String verbToString = exampleVerb.toStringByTranslation();
		// then
		assertEquals(expectedToString, verbToString);
	}

	@Test
	void shouldMarkTwoVerbsAsEqualIfInfinitiveIsEqual() {
		// given
		Verb verbToCompare = new Verb("wziąć", "take", "test", "test2", 0);
		// when
		int comparingResult = exampleVerb.compareTo(verbToCompare);
		// then
		assertThat(comparingResult, equalTo(0));
	}

	@Test
	void shouldNotMarkTwoVerbsAsEqualWhenInfinitiveIsDifferent() {
		// given
		Verb verbToCompare = new Verb("czytać", "read", "read", "read", 0);
		// when
		int comparingResult = exampleVerb.compareTo(verbToCompare);
		// then
		assertThat(comparingResult, not(equalTo(0)));
	}
}
