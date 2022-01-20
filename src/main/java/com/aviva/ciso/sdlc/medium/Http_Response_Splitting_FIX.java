package com.aviva.ciso.sdlc.medium;

import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * If dangerous data is sent through the headers of a response without checks, it can contain
 * linebreaks, which in some cases could cause the application to send more than one response.
 * used ??
 */
public class Http_Response_Splitting_FIX {
	/**
	 * encodeForUrl() will encode all dangerous symbols, including linebreaks.
	 */
	@GetMapping("Http_Response_Splitting/Safe/ESAPI")
	public void response(@RequestParam("inject") String inject, HttpServletResponse response) {

		/*** FIX ***/
		// Validate the header here
		inject = inject.replaceAll(System.getProperty("line.separator"), "");

		ESAPI.httpUtilities().addHeader("SAFE_HEADER", inject);
	}
}
