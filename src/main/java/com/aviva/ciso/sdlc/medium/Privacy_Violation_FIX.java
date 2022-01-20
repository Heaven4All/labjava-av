package com.aviva.ciso.sdlc.medium;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.aviva.ciso.sdlc.repositories.UserRepository;

public class Privacy_Violation_FIX {
	
	@Autowired
	UserRepository users;

	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*** FIX: ***/
		/**
		 * If the data transmitted is not sensitive, or if it is really supposed and safe
		 * to output where it is, then this is a FALSE POSITIVE.
		 * 
		 * Otherwise sensitive data should not be output in the logs for example,
		 * so remove the lines doing that.
		 */
	}
}
