package com.aviva.ciso.sdlc.high;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.aviva.ciso.sdlc.entities.User;
import com.aviva.ciso.sdlc.repositories.UserRepository;

/**
 * --------------------------------- Stored_XSS:
 * ---------------------------------
 * 
 * The application outputs untrusted data to the user without any validation or
 * sanitization. There is little differences with XSS_Reflected_All_Clients.
 */

@Controller
public class Stored_XSS_FIX {

	@Autowired
	private UserRepository users;

	@GetMapping("Stored_XSS/Safe/addUser")
	public void addUser(@ModelAttribute("inject") String username) {
		users.addUser(username);
	}

	@GetMapping("Stored_XSS/Safe/printUsers")
	public void printUsers(HttpServletResponse response) throws IOException {

		List<User> lastUsers = users.getUsersInOrderSafe("id");
		if (lastUsers == null || lastUsers.isEmpty()) {
			response.getWriter().write("<html><body><p> No users in database </p></body></html>");
		}

		StringBuilder result = new StringBuilder();
		for (User user : lastUsers) {
			result.append(user.getUsername() + "\r\n");
		}

		/*** FIX ***/
		String sanitizedResult = ESAPI.encoder().encodeForHTML(result.toString());

		response.getWriter().write("<html><body><p>" + sanitizedResult + "</p></body></html>");
	}

	// TODO: encodeForHtmlElement example
}
