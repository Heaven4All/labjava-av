package com.aviva.ciso.sdlc.medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Here a request is done to the parameterized url without any validation.
 * We could be sending and/or receiving data to/from anyone which could
 * be dangerous in the context of the function.
 */
public class SSRF {
	
	/**
	 * Here we output data from an unverified third party.
	 * This could cause at least an XSS injection.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		String url = request.getParameter("url");
		URL u = new URL(url);
		InputStreamReader sr = new InputStreamReader(u.openConnection().getInputStream());
		
		PrintWriter out = response.getWriter();
		BufferedReader reader = new BufferedReader(sr);
		String line = reader.readLine();
		while (line != null) {
			out.write(line);
			line = reader.readLine();
		}
	}
}
