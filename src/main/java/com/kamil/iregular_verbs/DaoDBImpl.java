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

public class DaoDBImpl implements Dao {

	private Connection conn;

	private static final String DB_NAME = "verbs.db";
	private static final String SOURCE_PATH = "jdbc:sqlite:" + new File("").getAbsolutePath() + File.separator;
	private static final String CONNECTION_STRING = SOURCE_PATH + DB_NAME;
	private static final String TABLE_NAME = "verbs";

	private Set<Verb> verbsCollectionsFromDB = new TreeSet<>();

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

		try (Statement statement = conn.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);

			while (resultSet.next()) {
				String infinitive = resultSet.getString("infinitive");
				String pastTense = resultSet.getString("pastTense");
				String pastParticiple = resultSet.getString("pastParticiple");
				String translation = resultSet.getString("translation");
				boolean isVerbLearnt = false;

				// SQLite do not use boolean values so in DB we use 0 and 1 values to mark as
				// learnt or unlearned
				if (resultSet.getInt("isLearnt") == 1) {
					isVerbLearnt = true;
				}

				Verb verb = new Verb(infinitive, pastTense, pastParticiple, translation, isVerbLearnt);

				verbsCollectionsFromDB.add(verb);
			}
		} catch (SQLException e) {
			System.out.println("Failure while reading data from database...");
			e.printStackTrace();
		}

		closeDatabase();

		return verbsCollectionsFromDB;
	}

	@Override
	public void saveVerbsToSource(Set<Verb> allVerbsAfterUserChanges) {

		openDatabase();

		addNewVerbsToDB(allVerbsAfterUserChanges);

		updateVerbsInDB(allVerbsAfterUserChanges);
		
		closeDatabase();
		
	}

	private void addNewVerbsToDB(Set<Verb> allVerbsAfterUserChanges) {

		allVerbsAfterUserChanges.removeAll(verbsCollectionsFromDB);

		if (allVerbsAfterUserChanges.size() < 1) {
			return;
		}

		try (PreparedStatement statement = conn
				.prepareStatement("INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?)")) {

			for (Verb verb : allVerbsAfterUserChanges) {

				// SQLite do not use boolean values so in DB we use 0 and 1 values to mark as
				// learnt or unlearned
				int wasVerbMarkedAsLearnt = 0;
				if (verb.isLearnt()) {
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
	
	private void updateVerbsInDB(Set<Verb> allVerbsAfterUserChanges) {
		try (PreparedStatement statement = conn
				.prepareStatement("UPDATE " + TABLE_NAME + " SET isLearnt = ? WHERE infinitive = ?")) {

			for (Verb verb : allVerbsAfterUserChanges) {

				
				// SQLite do not use boolean values so in DB we use 0 and 1 values to mark as
				// learnt or unlearned
				int learntStatusInDB = 0;
				
				if(verb.isLearnt() == true) {
					learntStatusInDB = 1;
				}

				statement.setInt(1, learntStatusInDB); 
				statement.setString(2, verb.getInfinitive());

				statement.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
