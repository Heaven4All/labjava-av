package com.aviva.ciso.sdlc.medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Here a request is done to the parameted url without any validation.
 * We could be sending and/or receiving data to/from anyone which could
 * be dangerous in the context of the function.
 */
public class SSRF_FIX {
	
	/**
	 * Now we validate the url so we know who we are getting data from.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		String url = request.getParameter("url");

		/*** FIX: we validate with a whitelist. ***/
		Map<String, String> whitelist = new HashMap<>();
		whitelist.put("https://remote.com", "https://remote.com");
		whitelist.put("https://example.com", "https://example.com");		
		if (!whitelist.containsKey(url))
			return;
		URL u = new URL(whitelist.get(url));
		
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
