package com.aviva.ciso.sdlc.high;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aviva.ciso.sdlc.entities.User;
import com.aviva.ciso.sdlc.repositories.UserRepository;

@RestController
public class SQL_Injection_FIX {
	
	@Autowired
	private UserRepository users;
	
	@GetMapping("SQL_Injection/Safe/JDBC")
	public String findByUsername(@RequestParam("inject") String username) {
		String email = "";
		
		/*** FIX ***/
		// Validation: we want a word containing only letters and numbers.
		if (users == null || !username.matches("[a-zA-Z1-9]+")) {
			return "Invalid username.";
		}
		// Sanitization: see User.findByUsernameSafe
		List<User> result = users.findByUsernameSafe(username);
		
		if (result.size() == 1) {
			email = result.get(0).getEmail();
		}

		if (email.isEmpty()) {
			return "User not found.";
		}
		return "You can contact the user via this email address: " + email;
	}
	
	@GetMapping("SQL_Injection/Safe/OrderBy")
	public String getAllUsersOrderBy(@RequestParam("inject") String column) {
		if (users == null) {
			return "No user in database.";
		}
		
		/*** FIX ***/
		List<User> result = users.getUsersInOrderSafe(column);

		
		if (result == null) {
			return "Invalid column name.";
		}
		return result.toString();
	}

}
