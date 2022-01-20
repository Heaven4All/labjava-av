package com.aviva.ciso.sdlc.high;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aviva.ciso.sdlc.entities.User;
import com.aviva.ciso.sdlc.repositories.UserRepository;

public class Second_Order_SQL_Injection_FIX {
	
	@Autowired
	private UserRepository users;

	@GetMapping("Second_Order_SQL_Injection/Unsafe/JDBC")
	public String addNewUser(@RequestParam("inject") String username) {
		if (users == null) {
			return "No user in database.";
		}

		/*** XXX ***/
		List<User> user = users.findByUsernameSafe(username);
		List<User> user2 = users.findByUsernameSafe(user.get(0).getUsername());
		return "User found.";
	}
}
