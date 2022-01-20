package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.aviva.ciso.sdlc.entities.User;
import com.aviva.ciso.sdlc.repositories.UserRepository;

/**
 * We must be sure that we are using TLS/HTTPS before transmitting
 * sensitive data somewhere. If this is not the case then we have to encrypt
 * the data before sending it on an unsecure channel.
 */
public class Cleartext_Submission_Of_Sensitive_Information_FIX {
	@Autowired
	private UserRepository users;

	public void sendSensitiveData(ServletRequest request, ServletResponse response) throws IOException {
		/*** FIX: send only on secure communication ***/
		if (request.isSecure()) {
			String username = request.getParameter("username");
			List<User> result = users.findByUsernameSafe(username);
			if (result.size() != 1) {
				response.getOutputStream().write("Error: invalid username".getBytes());
			} else {
				response.getOutputStream().write(result.get(0).getSsn().getBytes());
			}
		}
		
		response.getOutputStream().write("Error: not secure communication".getBytes());
	}
}
