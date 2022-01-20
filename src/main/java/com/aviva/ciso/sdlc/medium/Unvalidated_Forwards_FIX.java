package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class Unvalidated_Forwards_FIX {
	/*** FIX: Use_Of_Cryptographically_Weak_PRNG, Use_Of_Insuficiently_Random_Values,
	  Cross_Site_History_Manipulation ***/
	private SecureRandom random = new SecureRandom();

	@GetMapping("Unvalidated_Forwards/Safe/Switch")
	public void redirect(@RequestParam("inject") String target, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*** FIX ***/
		RequestDispatcher dispatcher;
		switch (target) {
		case "/admin":
		    dispatcher = request.getRequestDispatcher("/admin?r=" + random.nextInt());
		    break;
		case "/login":
		    dispatcher = request.getRequestDispatcher("/login?r=" + random.nextInt());
		    break;
		default:
		    dispatcher = request.getRequestDispatcher("/error?r=" + random.nextInt());
		}
		dispatcher.forward(request, response);
	}

	/** NOTE: Example of false positive **/
	/*
	@GetMapping("Unvalidated_Forwards/Safe/Whitelist")
	public void redirectW(@RequestParam("inject") String target, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		Map<String, String> whitelist = new HashMap<>();
		whitelist.put("/admin", "/admin");
		whitelist.put("/login", "/login");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/error?r=" + random.nextInt());
		if (whitelist.containsKey(target)) {
			dispatcher = request.getRequestDispatcher(whitelist.get(target));
		}
		dispatcher.forward(request, response);
	}*/
}
