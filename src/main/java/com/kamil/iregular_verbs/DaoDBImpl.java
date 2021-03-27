package com.kamil.iregular_verbs;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public class DaoDBImpl implements Dao{

	private Connection conn;
	
	private static final String DB_NAME = "verbs.db";
	private static final String SOURCE_PATH = "jdbc:sqlite:" + new File("").getAbsolutePath() + File.separator;
	private static final String CONNECTION_STRING = SOURCE_PATH + DB_NAME;
	private static final String TABLE_NAME = "verbs";
	
	private void openDatabase() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
		} catch (SQLException e) {
			System.out.println("Cannot connect to database.");
			e.printStackTrace();
		}
	}
	
	private void closeDatabase() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Cannot properly close database connection");
			e.printStackTrace();
		}
	}
	
	@Override
	public Set<Verb> getVerbsFromSource() {
		
		openDatabase();
		
		Set<Verb> verbsCollections = new TreeSet<>();
		
		try (Statement statement = conn.createStatement()){
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
			
			while (resultSet.next()) {
				String infinitive = resultSet.getString("infinitive");
				String pastTense = resultSet.getString("pastTense");
				String pastParticiple = resultSet.getString("pastParticiple");
				String translation = resultSet.getString("translation");
				
				boolean isVerbLearnt = false;
					if(resultSet.getInt("isLearnt") == 1) {
						isVerbLearnt = true;
					}
 
				Verb verb = new Verb(infinitive, pastTense, pastParticiple, translation, isVerbLearnt);
				verbsCollections.add(verb);
			}
		} catch (SQLException e) {
			System.out.println("Failure while reading data from database...");
			e.printStackTrace();
		}
	//	closeDatabase();
		return verbsCollections;
	}

	@Override
	public void saveVerbsToSource(Set<Verb> allVerbs) {
		
		openDatabase();
		
		Set<Verb> originalCollectionFromDB = getVerbsFromSource();
		
		allVerbs.removeAll(originalCollectionFromDB);
		
		addNewVerbsToDB(allVerbs);
		
		
		
	//	closeDatabase();
	}

	private void addNewVerbsToDB(Set<Verb> newVerbs) {
		openDatabase();
		try (PreparedStatement statement = conn.prepareStatement("INSERT INTO " + DB_NAME + " VALUES (?, ?, ?, ?, ?)")){
			for(Verb verb : newVerbs) {
				
				int wasVerbMarkedAsLearnt = 0;
				if(verb.isLearnt()) {
					wasVerbMarkedAsLearnt = 1;
				}
				
				statement.setString(1, verb.getInfinitive());
				statement.setString(2, verb.getPastTense());
				statement.setString(3, verb.getPastParticiple());
				statement.setString(4, verb.getTranslation());
				statement.setInt(5, wasVerbMarkedAsLearnt);
				
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



}
