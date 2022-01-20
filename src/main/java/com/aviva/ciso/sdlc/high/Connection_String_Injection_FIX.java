package com.aviva.ciso.sdlc.high;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class Connection_String_Injection_FIX {
	@GetMapping("Code_Injection/Unsafe/")
	public String connectionIjection(@RequestParam("url") String url) throws SQLException {
		/*** FIX: We use whitelist for every string that goes inside. ***/
		HashMap<String, String> whitelist = new HashMap<String, String>();
		whitelist.put("url1", "url1");
		whitelist.put("url2", "url2");
		DriverManager.getConnection(whitelist.get(url));
		
		return "";
	}
}
