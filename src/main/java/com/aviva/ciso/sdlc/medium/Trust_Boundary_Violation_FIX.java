package com.aviva.ciso.sdlc.medium;

import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * ---------------------------------
 * Trust_Boundary_Violation:
 * ---------------------------------
 * 
 * This happens when untrusted data is inserted in session attributes.
 * Session attributes MUST ALWAYS be trusted, so everything that goes in a seesion attribute must
 * be properly validated and sanitized.
 * 
 * Ideally, there should not be user-supplied input inside the attributes of a session.
 */

@RestController
public class Trust_Boundary_Violation_FIX {
	@GetMapping(value="Trust_Boundary_Violation/Safe/Session")
	public String sessionInject(@ModelAttribute("inject") String inject,  HttpSession session)
			throws ValidationException {
		
		/*** FIX ***/
		String description = "User email";
		String input = inject;
		String type = "Email";
		int maxLen = 50;
		boolean nullable = false;
		boolean canonicalize = false;
		
		inject = ESAPI.validator().getValidInput(description, input, type, maxLen, nullable, canonicalize);
		session.setAttribute("inject", inject);
		return "";
	}
}
