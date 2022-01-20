package com.aviva.ciso.sdlc.high;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aviva.ciso.sdlc.entities.User;
import com.aviva.ciso.sdlc.repositories.UserRepository;

/**
 * With an SQL injection a database is usually easy to compromize.
 * This type of flaw should be corrected immediately.
 */
@RestController
public class SQL_Injection {
	
	@Autowired
	private UserRepository users;

	@GetMapping("SQL_Injection/Unsafe/JDBC")
	public String findByUsername(@RequestParam("inject") String username) {

		if (users == null) {
			return "No user in database.";
		}

		/*** XXX ***/
		List<User> result = users.findByUsernameUnsafe(username);
		String email = "";
		if (result.size() == 1) {
			email = result.get(0).getEmail();
		}

		if (email.isEmpty()) {
			return "User not found.";
		}
		return "You can contact the user via this email address: " + email;
	}

	@GetMapping("SQL_Injection/Unsafe/OrderBy")
	public String getAllUsersOrderBy(@RequestParam("inject") String column) {
		if (users == null) {
			return "No user in database.";
		}

		/*** XXX ***/
		List<User> result = users.getUsersInOrderUnsafe(column);

		if (result == null) {
			return "Invalid column name.";
		}
		return result.toString();
	}
}
