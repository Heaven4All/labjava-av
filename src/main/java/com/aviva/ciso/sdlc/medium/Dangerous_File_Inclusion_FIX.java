package com.aviva.ciso.sdlc.medium;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;

public class Dangerous_File_Inclusion_FIX {
	private static final String LIB_DIR = "libs";

	@GetMapping(value = "Dangerous_File_Inclusion/Safe")
	public void loadLib(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lib = request.getParameter("libName");

		/*** FIX: we use a whitelist. ***/
		String libName = "";
		if (lib.equals("user"))
			libName = LIB_DIR + "/userLib.jsp";
		else if (lib.equals("special"))
			libName = LIB_DIR + "/specialUserLib.jsp";
		else
			libName = LIB_DIR + "/anonymousLib.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(libName);
		dispatcher.include(request, response);
	}
}
