package com.aviva.ciso.sdlc.high;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * ---------------------------------
 * Reflected_XSS_All_Clients:
 * ---------------------------------
 * 
 * The application outputs untrusted data to the user without any validation or sanitization.
 * For example, if a webpage is modified directly by the parameters of a GET request,
 * the client only needs to follow a link to be potentially compromised.
 * 
 * 		https://banque.com/index.html?prenom=Jean&nom=<script>alert()</script>
 * 
 * You can see that if someone can trigger a javascript alert(), they can do what they want in javascript,
 * like exploiting oher vulnerabilities. For example if we pair this with cookies that are not HTTPONLY,
 * they can get all the cookies of a user on the website, and impersonate them this way (@see HttpOnlyCookies).
 */
@RestController
public class Reflected_XSS_All_Clients_FIX {
	/**
	 * *** FIX ***
	 * c:out in 'webapp/WEB-INF/jsp/Reflected_XSS_All_Clients/safe.jsp'
	 * sanitizes before outputting the data, which makes it safe.
	 */
	@GetMapping("Reflected_XSS_All_Clients/Safe/JSP")
	public ModelAndView jsp(@RequestParam("inject") String inject) {
		// Do not forget to validate
		/*** FIX ***/
		ModelAndView view = new ModelAndView("safe");
		view.addObject("inject", inject);
		return view;
	}

	@GetMapping("Reflected_XSS_All_Clients/Unsafe/Response")
	public void session(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String inject = request.getParameter("inject");
		
		/*** FIX: if the output is HTML then use encodeForHTML. ***/
		// validate input here.
		String sanitized = ESAPI.encoder().encodeForHTML(inject);
		
		response.getWriter().write("<html><body><p>"+ sanitized + "</p></body></html>");
	}
}