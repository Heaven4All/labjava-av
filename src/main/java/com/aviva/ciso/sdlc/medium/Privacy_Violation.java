package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.aviva.ciso.sdlc.entities.User;
import com.aviva.ciso.sdlc.repositories.UserRepository;

/**
 * Privacy_Violation is caused by confidential data sent on the network or shown in the logs.
 */
public class Privacy_Violation {

	@Autowired
	UserRepository users;
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.isSecure()) {
			String username = request.getParameter("username");
			List<User> result = users.findByUsernameSafe(username);
			if (result.size() != 1)
				response.getOutputStream().write("Error: invalid username".getBytes());
			response.getOutputStream().write(result.get(0).getSsn().getBytes());
		}
		
		response.getOutputStream().write("Error: not secure communication".getBytes());
	}
}
