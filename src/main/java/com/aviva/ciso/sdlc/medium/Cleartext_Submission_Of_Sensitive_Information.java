package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.aviva.ciso.sdlc.entities.User;
import com.aviva.ciso.sdlc.repositories.UserRepository;

/**
 * We must be sure that we are using TLS/HTTPS before transmitting
 * sensitive data somewhere. If this is not the case then we have to encrypt
 * the data before sending it on an unsecure channel.
 */
public class Cleartext_Submission_Of_Sensitive_Information {
	@Autowired
	private UserRepository users;
	
	public void sendSensitiveData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		List<User> result = users.findByUsernameSafe(username);
		if (result.size() != 1) {
			response.getOutputStream().write("Error: invalid username".getBytes());
		}
		
		/** Here getSsn may be detected as "Social Security Number", which would be sensitive data.
		 * NOTE: also causes a Privacy_Violation
		 **/
		response.getOutputStream().write(result.get(0).getSsn().getBytes());
	}

}
