package com.kamil.iregular_verbs;


public class Main {

	public static void main(String[] args) {
		
		DaoDBImpl dao = new DaoDBImpl();
			
		CollectionManager manager = new CollectionManager(dao);
		
		manager.populateCollections();
		
		var main = manager.getMainCollection().getAllVerbsSortedByInfinitive();
		main.forEach(System.out::println);
	
		Verb verb = new Verb("test", "test", "test", "test", false);
		manager.addVerbToMainCollection(verb);
		
		manager.saveCollections();
		
	
//		Verb verb = new Verb("ffF", "ffF", "ffF", "ffF", false);
//		manager.addVerbToMainCollection(verb);
//	
//		dao.saveVerbsToSource(manager.gatherAllVerbsIntoOneCollection());


	}

}
