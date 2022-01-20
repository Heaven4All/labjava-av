package com.aviva.ciso.sdlc.medium;

import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * If dangerous data is sent through the headers of a response without checks, it can contain
 * linebreaks, which in some cases could cause the application to send more than one response.
 */
public class Http_Response_Splitting {

	@GetMapping("Http_Response_Splitting/Unsafe/")
	public String unsafeResponse(@RequestParam("inject") String inject, HttpServletResponse response) {
		ESAPI.httpUtilities().addHeader("UNSAFE_HEADER", inject);
		return "";
	}
}
