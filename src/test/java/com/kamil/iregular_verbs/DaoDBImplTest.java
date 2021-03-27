package com.kamil.iregular_verbs;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DaoDBImplTest {

	private Connection conn;
	
	private static final String DB_NAME = "verbs.db";
	private static final String SOURCE_PATH = new File("").getAbsolutePath() + File.separator;
	private static final String CONNECTION_STRING = SOURCE_PATH + DB_NAME;
	
	void openDatabase() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
