package com.kamil.iregular_verbs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class VerbsCollection {
	
	Collection<Verb> verbsCollection;
	
	public VerbsCollection() {
		this.verbsCollection = new TreeSet<Verb>();
	}
	
	public Collection<Verb> getAllVerbsSortedByInfinitive() {
		return this.verbsCollection;
	}
	
	public Collection<Verb> getAllVerbsSortedByTranslation() {
		return this.verbsCollection.stream()
				.sorted( (v1, v2) -> v1.getTranslation().compareTo(v2.getTranslation()))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public boolean addVerb(Verb verb) {
		return this.verbsCollection.add(verb);
	}
	
	public boolean removeVerb(Verb verb) {
		if(this.verbsCollection.contains(verb)) {
			return this.verbsCollection.remove(verb);	
		}else {
			return false;
		}
	}

	
}
