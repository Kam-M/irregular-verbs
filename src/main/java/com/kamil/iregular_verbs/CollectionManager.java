package com.kamil.iregular_verbs;

import java.util.Set;
import java.util.TreeSet;

public class CollectionManager {
	
	private VerbsCollection mainCollection;
	private VerbsCollection learntCollection;
	
	private Dao dao;
	
	public CollectionManager(Dao dao) {
		this.dao = dao;
		this.mainCollection = new VerbsCollection();
		this.learntCollection = new VerbsCollection();
	}

	public VerbsCollection getMainCollection() {
		return mainCollection;
	}

	public VerbsCollection getLearntCollection() {
		return learntCollection;
	}
	
	public boolean addVerbToMainCollection(Verb verb) {
		if(verb != null) {
			return this.mainCollection.addVerb(verb);
		}else {
			return false;
		}
	}

	public void splitVerbsIntoProperCollections() {
		
		Set<Verb> verbsFromSource = dao.getVerbsFromSource();
		
		for(Verb verb : verbsFromSource) {
			if(verb.isLearnt()) {
				learntCollection.addVerb(verb);
			}else {
				mainCollection.addVerb(verb);
			}
		}
	}

	public Set<Verb> gatherVerbsIntoOne() {
		Set<Verb> allVerbsIntoOne = new TreeSet<>();
		
		allVerbsIntoOne.addAll(this.mainCollection.getAllVerbsSortedByInfinitive());
		allVerbsIntoOne.addAll(this.learntCollection.getAllVerbsSortedByInfinitive());
		
		return allVerbsIntoOne;
	}

	public boolean moveVerbFromMainToLearnt(Verb verb) {
		boolean wasRemovedFromMain = this.mainCollection.removeVerb(verb);
		boolean wasAddedToLearnt = this.learntCollection.addVerb(verb);
			if(wasRemovedFromMain && wasAddedToLearnt) {
				return true;
			}else {
				return false;
			}
	}

	

}
