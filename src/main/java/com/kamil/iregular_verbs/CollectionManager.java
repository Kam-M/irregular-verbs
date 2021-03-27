package com.kamil.iregular_verbs;

import java.util.Set;

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

	

}
