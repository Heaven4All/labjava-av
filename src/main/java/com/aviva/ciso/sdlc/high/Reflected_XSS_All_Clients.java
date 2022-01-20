package com.aviva.ciso.sdlc.high;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class Reflected_XSS_All_Clients {
	/**
	 * @param inject: data to inject
	 * @return injection in a jsp without any protection.
	 */
	@GetMapping("Reflected_XSS_All_Clients/Unsafe/JSP")
	public ModelAndView jsp(@ModelAttribute("inject") String inject) {
		ModelAndView view = new ModelAndView("unsafe");
		view.addObject("inject", inject);
		return view;
	}

	@GetMapping("Reflected_XSS_All_Clients/Unsafe/Response")
	public void session(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String inject = request.getParameter("inject");
		response.getWriter().write("<html><body><p>"+ inject + "</p></body></html>");
	}
	
	public void sessionTest1(HttpServletRequest request, HttpSession session) {
		session.setAttribute("inject", request.getParameter("test"));
	}
	
	public ModelAndView sessionTest2(HttpServletRequest request, HttpSession session) {
		String test = ESAPI.encoder().encodeForDN(request.getParameter("test"));
		session.setAttribute("sessionInject", test);
		return new ModelAndView("unsafe_session");
	}
}
