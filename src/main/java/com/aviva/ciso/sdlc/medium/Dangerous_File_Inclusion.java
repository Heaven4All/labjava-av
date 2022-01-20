package com.aviva.ciso.sdlc.medium;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;


/**
 * We use the content of a file without sanitization or
 * validation of the path.
 */
public class Dangerous_File_Inclusion {
	@GetMapping(value="Dangerous_File_Inclusion/Unsafe")
	public void loadLib(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		String libName = request.getParameter("libName");
		RequestDispatcher dispatcher = request.getRequestDispatcher(libName);
		dispatcher.include(request, response);
	}
}
