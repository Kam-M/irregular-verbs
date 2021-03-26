package com.kamil.iregular_verbs;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VerbTest {

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testToStringByTranslation() {
		fail("Not yet implemented");
	}

	@Test
	void shouldMarkTwoVerbsAsEqualIfInfinitiveIsEqual() {
		//given
		Verb verb1 = new Verb("brać", "take", "took", "taken");
		Verb verb2 = new Verb("wziąć", "take", "test", "test2");
		//when
		int comparingResult = verb1.compareTo(verb2);
		//then
		assertThat(comparingResult, equalTo(0));
	}
	
	@Test
	void shouldNotMarkTwoVerbsAsEqualWhenInfinitiveIsDifferent() {
		//given
		Verb verb1 = new Verb("brać", "take", "took", "taken");
		Verb verb2 = new Verb("czytać", "read", "read", "read");
		//when
		int comparingResult = verb1.compareTo(verb2);
		//then
		assertThat(comparingResult, not(equalTo(0)));

	}

}
