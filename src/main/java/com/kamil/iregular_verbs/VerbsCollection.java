package com.kamil.iregular_verbs;

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
				.collect(Collectors.toCollection( () -> new TreeSet<Verb>(( verb1, verb2) -> 
				verb1.getTranslation().compareTo(verb2.getTranslation()))));
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
