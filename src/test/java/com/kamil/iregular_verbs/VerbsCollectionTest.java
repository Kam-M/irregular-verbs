package com.kamil.iregular_verbs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.Test;

class VerbsCollectionTest {

	@Test
	void shouldAddGivenVerbToCollection() {
		// given
		Verb verb = new Verb("brać", "take", "took", "taken");
		VerbsCollection verbsCollection = new VerbsCollection();
		// when
		boolean wasAddedToCollection = verbsCollection.addVerb(verb);
		// then
		assertTrue(wasAddedToCollection);
		assertEquals(1, verbsCollection.getAllVerbsSortedByInfinitive().size());
	}

	@Test
	void shouldRemoveGivenVerbFromCollection() {
		// given
		Verb verb = new Verb("brać", "take", "took", "taken");
		VerbsCollection verbsCollection = new VerbsCollection();
		verbsCollection.addVerb(verb);
		// when
		boolean wasRemovedFromCollection = verbsCollection.removeVerb(verb);
		// then
		assertTrue(wasRemovedFromCollection);
		assertEquals(0, verbsCollection.getAllVerbsSortedByInfinitive().size());
	}

	@Test
	void shouldDoNothingWhenAskedForRemoveVerbNotPresentInCollection() {
		// given
		Verb verb = new Verb("brać", "take", "took", "taken");
		Verb verb2 = new Verb("czytać", "read", "read", "read");

		VerbsCollection verbsCollection = new VerbsCollection();
		verbsCollection.addVerb(verb);

		// when
		boolean wasVerbRemoved = verbsCollection.removeVerb(verb2);

		// then
		assertFalse(wasVerbRemoved);
		assertEquals(1, verbsCollection.getAllVerbsSortedByInfinitive().size());

	}

	@Test
	void shouldReturnAllVerbsFromCollectionSortedByInfinitive() {
		// given
		Verb verb1 = new Verb("brać", "take", "took", "taken");
		Verb verb2 = new Verb("czytać", "read", "read", "read");
		Verb verb3 = new Verb("dzwonić", "ring", "rang", "rung");

		VerbsCollection verbsCollection = new VerbsCollection();

		verbsCollection.addVerb(verb1);
		verbsCollection.addVerb(verb2);
		verbsCollection.addVerb(verb3);
		// when
		Collection<Verb> collectionOfVerbs = verbsCollection.getAllVerbsSortedByInfinitive();
		var collectionToArray = collectionOfVerbs.toArray();
		// then
		assertEquals(3, collectionOfVerbs.size());
		assertThat(collectionToArray[2], is(verb1));
	}

	@Test
	void shouldReturnAllVerbsFromCollectionSortedByTranslation() {
		// given
		Verb verb1 = new Verb("brać", "take", "took", "taken");
		Verb verb2 = new Verb("czytać", "read", "read", "read");
		Verb verb3 = new Verb("dzwonić", "ring", "rang", "rung");

		VerbsCollection verbsCollection = new VerbsCollection();

		verbsCollection.addVerb(verb1);
		verbsCollection.addVerb(verb2);
		verbsCollection.addVerb(verb3);
		// when
		Set<Verb> collectionOfVerbs = verbsCollection.getAllVerbsSortedByTranslation();
		var collectionToArray = collectionOfVerbs.toArray();
		// then
		assertEquals(3, collectionOfVerbs.size());
		assertThat(collectionToArray[0], is(verb1));
	}

}
