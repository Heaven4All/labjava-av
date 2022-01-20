package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aviva.ciso.sdlc.repositories.UserRepository;

/**
 * A malicious javascript script could determine if the user
 * is logged-in, is admin, or anything that would cause a
 * redirection after an "if" test.
 */
public class Cross_Site_History_Manipulation_FIX {
	@Autowired
	private UserRepository users;

	/**
	 * The solution is to add a random string in the url, this way
	 * it will always be added in the history object.
	 */
	@GetMapping("Cross_Site_History_Manipulation/Safe/")
	public String redirectIfAdmin(@RequestParam("inject") String username, HttpServletResponse response)
			throws IOException {
		/*** FIX ***/
		SecureRandom random = new SecureRandom();

		if (users.isAdmin(username)) {
			/*** FIX ***/
			response.sendRedirect("/error?r=" + random.nextInt());
		}
		return "";
	}

}
