package com.kamil.iregular_verbs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DaoDBImplTest {

	private DaoDBImpl dao;

	@BeforeEach
	void setUp() {
		dao = new DaoDBImpl();
		dao.setDBName("testDB.db");
		populateDatabaseWithTestData();
	}

	private void populateDatabaseWithTestData() {
		dao.openDatabase();

		String createTable = "CREATE TABLE IF NOT EXISTS verbs (infinitive TEXT PRIMARY KEY, pastTense TEXT NOT NULL, pastParticiple TEXT NOT NULL, translation TEXT NOT NULL, isLearnt INTEGER NOT NULL);";

		String insertDataIntoTable = "INSERT INTO verbs VALUES('read', 'read2', 'read3', 'czytać', 0);";
			

		try (Statement statement = dao.getConn().createStatement();) {
			statement.executeUpdate(createTable);
			statement.executeUpdate(insertDataIntoTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	@AfterEach
//	void cleanUpAfterTest() {
//		String truncateTable = "TRUNCATE TABLE verbs;";
//
//		try (Statement statement = dao.getConn().createStatement();) {
//			statement.executeUpdate(truncateTable);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	void test() {
		assertTrue(true);
	}

}
