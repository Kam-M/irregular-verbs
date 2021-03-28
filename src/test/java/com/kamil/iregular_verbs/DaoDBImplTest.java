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

	private static DaoDBImpl daoImpl;
//	private static Connection conn;

	@BeforeAll
	static void setUp() {
		daoImpl = new DaoDBImpl();
		daoImpl.setDBName("testDB.db");
	}
	
	
	@BeforeEach
	void populateDB() {
		
		daoImpl.openDatabase();
		
		String createTable = "CREATE TABLE \"verbs\" (\"infinitive\" TEXT NOT NULL UNIQUE,\"pastTense\" TEXT NOT NULL,"
				+ "\"pastParticiple\" TEXT NOT NULL,\"translation\" TEXT NOT NULL,\"isLearnt\" INTEGER NOT NULL,"
				+ "PRIMARY KEY(\"infinitive\"))";
		String insertRows = "INSERT INTO " + daoImpl.getTableName() + " VALUES " +
				"(\"take\", \"took\", \"taken\", \"brać\", 0),"
				+ "(\"ring\", \"rang\", \"rung\", \"dzwonić\", 0),"
				+ "(\"see\", \"saw\", \"seen\", \"widzieć\", 0)";
		
		try (Statement statement = daoImpl.getConn().createStatement()){
			statement.executeUpdate(createTable);
	//		statement.executeUpdate(insertRows);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		daoImpl.closeDatabase();
	}
	
//	@AfterEach
//	void cleanUp() {
//		
//		daoImpl.openDatabase();
//		
//		try (Statement statement = daoImpl.getConn().createStatement()){
//			String cleanDB = "DROP TABLE " + daoImpl.getTableName();
//			statement.executeUpdate(cleanDB);
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		
//		daoImpl.closeDatabase();
//	}

//	@AfterAll
//	static void tearDown() {
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	@Test
	void should() {
		assertTrue(true);
	}

}
