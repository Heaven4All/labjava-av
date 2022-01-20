package com.aviva.ciso.sdlc.medium;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A redirection is done without validation of the url.
 */
public class Unvalidated_Forwards {
	@GetMapping("Unvalidated_Forwards/Unsafe/")
	public void redirect(@RequestParam("inject") String target, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		/*** XXX ***/
		RequestDispatcher dispatcher = request.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}
}
