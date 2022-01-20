package com.aviva.ciso.sdlc.medium;

import java.io.IOException;

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
public class Cross_Site_History_Manipulation {
	@Autowired
	private UserRepository users;

	/**
	 * Here, a script could take the size of history object after having put the user
	 * through /error. Then redirect to the endpoint below. If the history
	 * size has changed, then the user is an admin, else they would have been
	 * redirected to /error which was already the last entry in history so
	 * history size would have stayed the same.
	 */
	@GetMapping("Cross_Site_History_Manipulation/Unsafe/")
	public String redirectIfAdmin(@RequestParam("inject") String username, HttpServletResponse response)
			throws IOException {

		if (users.isAdmin(username)) {
			response.sendRedirect("/error");
		}
		return "";
	}

}
