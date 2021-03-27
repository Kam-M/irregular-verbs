package com.kamil.iregular_verbs;

public class Main {

	public static void main(String[] args) {
		
		DaoDBImpl dao = new DaoDBImpl();
			
		var set = dao.getVerbsFromSource();
		
		set.forEach(System.out::println);
		
		dao.saveVerbsToSource(null);

	}

}
