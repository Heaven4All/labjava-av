package com.aviva.ciso.sdlc.validation.spring;

import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TestTrustBoundaryViolation {
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	@GetMapping(value="TestValidation/Safe/Session")
	public String sessionInject(@ModelAttribute("inject") String inject,  HttpSession session) {
		AgeForm form = new AgeForm();
		form.setAge(inject);
		
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<AgeForm>> violations = validator.validate(form);
		
		// Log errors
		/*
		for (ConstraintViolation<AgeForm> violation : violations) {
			logger.error(violation.getMessage());
		} */
		
		if (!violations.isEmpty()) {
			throw new ValidationException("AgeForm: invalid age.");
		}
		
		session.setAttribute("inject", form.getAge());
		return "";
	}
}
