package com.aviva.ciso.sdlc.medium;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * ---------------------------------
 *  CGI_Reflected_XSS_All_Clients:
 * ---------------------------------
 * 
 * The application outputs untrusted data to the user without any validation or
 * sanitization. There is little differences with XSS_Reflected_All_Clients.
 */
@RestController
public class CGI_Reflected_XSS_All_Clients_FIX {
	/**
	 * @param inject: data to inject
	 * @throws IOException 
	 */
	@GetMapping("CGI_Reflected_XSS_All_Clients/Safe/JSP")
	public String jspUnsafe(@ModelAttribute("inject") String inject, HttpServletResponse response) throws IOException {
		/*** FIX ***/
		inject = ESAPI.encoder().encodeForHTML(inject);

		response.setContentType("text/html");
		//System.out.println("Content-Type: \n\n");
		response.getWriter().print("<html>The received string is " + inject + "</html>");

		return "Thank you, we received your message.";
	}
}
