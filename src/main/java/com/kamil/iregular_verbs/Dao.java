package com.kamil.iregular_verbs;

import java.util.Set;

public interface Dao {
	
	Set<Verb> getVerbsFromSource();
	void saveVerbsToSource(Set<Verb> collectionOfVerbs);

}
