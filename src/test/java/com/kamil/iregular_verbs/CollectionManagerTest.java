package com.kamil.iregular_verbs;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import java.util.Set;
import java.util.TreeSet;

class CollectionManagerTest {
	
	@Test
	void shouldAddGivenVerbToMainCollection() {
		//given
		Verb verb = new Verb("take", "took", "taken", "brać", false);
		CollectionManager manager = new CollectionManager(new DaoDBImpl());
		//when
		boolean wasAdded = manager.addVerbToMainCollection(verb);
		//then
		assertTrue(wasAdded);
		assertThat(manager.getMainCollection().getAllVerbsSortedByInfinitive().size(), is(1));
	}
	
	@Test
	void shouldNotAddGivenVerbToMainCollectionWhenNull() {
		//given
		CollectionManager manager = new CollectionManager(new DaoDBImpl());
		//when
		boolean wasAdded = manager.addVerbToMainCollection(null);
		//then
		assertFalse(wasAdded);
		assertThat(manager.getMainCollection().getAllVerbsSortedByInfinitive().size(), is(0));
	}

	@Test
	void shouldSplitVerbsFromSourceIntoTwoSeparateCollections() {
		//given
		Dao dao = mock(DaoDBImpl.class);
		CollectionManager manager = new CollectionManager(dao);
		
		Set<Verb> testVerbs = new TreeSet<>();
		testVerbs.add(new Verb("take", "took", "taken", "brać", false));
		testVerbs.add(new Verb("ring", "rang", "rung", "dzwonić", true));
		testVerbs.add(new Verb("become", "became", "become", "zostać", false));
		
		given(dao.getVerbsFromSource()).willReturn(testVerbs);
		//when
		manager.splitVerbsIntoProperCollections();
		
		//then
		verify(dao).getVerbsFromSource();
		
		var mainCollection = manager.getMainCollection();
		var learntCollection = manager.getLearntCollection();
		
		assertThat(mainCollection.getAllVerbsSortedByInfinitive().size(), equalTo(2));
		assertThat(learntCollection.getAllVerbsSortedByInfinitive().size(), equalTo(1));
	}
	
	@Test
	void shouldReturnVerbsGatheredIntoOneCollection() {
		
	}

}
