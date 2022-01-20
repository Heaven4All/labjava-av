package com.aviva.ciso.sdlc.medium;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * ---------------------------------
 * CGI_Reflected_XSS_All_Clients:
 * ---------------------------------
 * 
 * The application outputs untrusted data to the user without any validation or sanitization.
 * There is little differences with XSS_Reflected_All_Clients.
 * 
 */

public class CGI_Reflected_XSS_All_Clients extends HttpServlet {
	/**
	 * @param inject: data to inject
	 * @throws IOException 
	 **/
	@GetMapping("CGI_Reflected_XSS_All_Clients/Unsafe/")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String inject = request.getParameter("inject");
		System.out.println("The received string is " + inject);
	}
}
