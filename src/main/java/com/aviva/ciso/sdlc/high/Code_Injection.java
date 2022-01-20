package com.aviva.ciso.sdlc.high;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class Code_Injection {
	@GetMapping("Code_Injection/Unsafe/")
	public String codeInjection(@RequestParam("inject") String code) throws ClassNotFoundException {
		
		/*** XXX ***/
		Class<?> example = Class.forName("com.example.class" + code);
		// Use imported class
		
		return "";
	}
}
