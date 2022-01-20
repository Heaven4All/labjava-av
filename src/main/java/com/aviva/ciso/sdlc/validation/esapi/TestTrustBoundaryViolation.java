package com.aviva.ciso.sdlc.validation.esapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TestTrustBoundaryViolation {

	// Token example with manual rule creation
	@GetMapping(value="TestValidation/Safe/Token")
	public String validToken(@ModelAttribute("inject") String inject,  HttpSession session)
			throws org.owasp.esapi.errors.ValidationException {

		TokenValidationRule tvr = new TokenValidationRule("Init", ESAPI.encoder());
		session.setAttribute("inject", tvr.getValid("TestValidation", inject));
		return "";
	}
	
	// French date example
	@GetMapping(value="TestValidation/Safe/Date")
	public String validDate(@ModelAttribute("inject") String inject,  HttpSession session)
			throws org.owasp.esapi.errors.ValidationException {

		DateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy");
		session.setAttribute("inject", ESAPI.validator().getValidDate("TestValidation", inject, frenchFormat, false));
		return "";
	}
	
	// French name example
	/* for a name a validation is maybe a bad idea, prefer sanitization */
	@GetMapping(value="TestValidation/Safe/Name")
	public String validName(@ModelAttribute("inject") String inject,  HttpSession session)
			throws org.owasp.esapi.errors.ValidationException {

		session.setAttribute("inject", ESAPI.validator().getValidInput("TestValidation", inject, "Nom", 30, false));
		return "";
	}
}
