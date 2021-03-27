package com.kamil.iregular_verbs;

import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		DaoDBImpl dao = new DaoDBImpl();
			
		CollectionManager manager = new CollectionManager(dao);
		
		manager.splitVerbsIntoProperCollections();
		
		Set<Verb> set = manager.getMainCollection().getAllVerbsSortedByInfinitive();
		
		manager.addVerbToMainCollection(new Verb("ggg", "g", "ff", "gg", false));
		
		set.forEach(System.out::println);
		
		dao.saveVerbsToSource(manager.gatherAllVerbsIntoOneCollection());

	}

}
