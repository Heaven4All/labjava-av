package com.aviva.ciso.sdlc.high;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class Connection_String_Injection {
	@GetMapping("Code_Injection/Unsafe/")
	public String connectionIjection(@RequestParam("url") String url) throws SQLException {
		/*** XXX ***/
		DriverManager.getConnection(url);
		
		return "";
	}
}
