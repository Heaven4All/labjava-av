package com.aviva.ciso.sdlc.high;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class Code_Injection_FIX {
	@GetMapping("Code_Injection/Safe/")
	public String codeInjection(@RequestParam("inject") String code) throws ClassNotFoundException {
		
		/*** FIX: we use a whitelist. We could also do a validation with ESAPI
		 * if 'code' only contains non dangerous characters, like alphanumerics. ***/
		HashMap<String, String> whitelist  = new HashMap<String, String>();
		whitelist.put("CODE1", "CODE1");
		whitelist.put("CODE2", "CODE2");
		whitelist.put("CODE3", "CODE3");

		/*** XXX ***/
		Class<?> example = Class.forName("com.example.class" + whitelist.get(code));
		// Use imported class

		return "";
	}
}
