package com.aviva.ciso.sdlc.medium;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

public class Session_Fixation_FIX {
	
	@GetMapping(value = "Session_Fixation/Safe/")
	public String logout(HttpSession session) {
		session.invalidate();
		return "Logged out";
	}
}
