package com.kamil.iregular_verbs;


public class Main {

	public static void main(String[] args) {
		
		DaoDBImpl dao = new DaoDBImpl();
			
		CollectionManager manager = new CollectionManager(dao);
		
		manager.splitVerbsIntoProperCollections();
		
		var main = manager.getMainCollection().getAllVerbsSortedByInfinitive();
		main.forEach(System.out::println);
		
		
//		Verb verb = new Verb("ffF", "ffF", "ffF", "ffF", false);
//		manager.addVerbToMainCollection(verb);
//	
//		dao.saveVerbsToSource(manager.gatherAllVerbsIntoOneCollection());


	}

}
