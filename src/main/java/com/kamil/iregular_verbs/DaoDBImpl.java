package com.kamil.iregular_verbs;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public class DaoDBImpl implements Dao{

	private Connection conn;
	
	private static final String DB_NAME = "verbs";
	public static final String SOURCE_PATH = "jdbc:sqlite:" + new File("").getAbsolutePath() + File.separator;
	public static final String CONNECTION_STRING = SOURCE_PATH + DB_NAME;
	private static final String TABLE_NAME = "verbs";
	
	void openDatabase() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
		} catch (SQLException e) {
			System.out.println("Cannot connect to database.");
			e.printStackTrace();
		}
	}
	
	@Override
	public Set<Verb> getVerbsFromSource() {
		Set<Verb> verbsCollections = new TreeSet<Verb>();
		
		try (Statement statement = conn.createStatement()){
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
			
			while (resultSet.next()) {
				String infinitive = resultSet.getString("infinitive");
				String pastTense = resultSet.getString("pastTense");
				String pastParticiple = resultSet.getString("pastParticiple");
				String translation = resultSet.getString("translation");
				int isLearnt = resultSet.getInt("isLearnt");
 
				Verb verb = new Verb(infinitive, pastTense, pastParticiple, translation, isLearnt);
				verbsCollections.add(verb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verbsCollections;
	}

	@Override
	public void saveVerbsToSource(Set<Verb> verbsCollection) {
		// TODO Auto-generated method stub
		
	}



}
