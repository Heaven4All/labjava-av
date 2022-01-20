package com.aviva.ciso.sdlc.medium;

import java.sql.SQLException;

public class Hardcoded_Password_In_Connection_String {
	
	private javax.sql.DataSource dataSource;

	public void connect() throws SQLException {
		dataSource.getConnection("admin", "admin");
		// do something
	}
}
