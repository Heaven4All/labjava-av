package com.aviva.ciso.sdlc.medium;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.aviva.ciso.sdlc.entities.User;
import com.aviva.ciso.sdlc.repositories.UserRepository;

public class CGI_Stored_XSS {

	@Autowired
	private UserRepository users;

	@GetMapping("Stored_XSS/Unsafe/addUser")
	public void addUser(@ModelAttribute("inject") String username) {
		users.addUser(username);
	}

	@GetMapping("Stored_XSS/Unsafe/printUsers")
	public void printUsers(HttpServletResponse response) {
		
		List<User> lastUsers = users.getUsersInOrderSafe("id");
		if (lastUsers == null || lastUsers.isEmpty()) {
			System.out.println("<html><body><p> No users in database </p></body></html>");
		}

		StringBuilder result = new StringBuilder();
		for (User user : lastUsers) {
			result.append(user.getUsername() + "\r\n");
		}
			
		System.out.println(result.toString());
	}
}
