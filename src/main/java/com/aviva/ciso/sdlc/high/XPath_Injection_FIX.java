package com.aviva.ciso.sdlc.high;

import javax.servlet.http.HttpServletRequest;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.owasp.esapi.ESAPI;

public class XPath_Injection_FIX {
	private String readUserGroup_Unsafe(HttpServletRequest request) 
			throws XPathExpressionException { 
		/*** FIX: As always you need to sanitize user input. ***/
		String userId = ESAPI.encoder().encodeForXML(request.getParameter("UserID"));
		String expr = "//USERS/USER[UserID/text()='" + userId + "']/GROUP/text()";
		
		XPath navigator = XPathFactory.newInstance().newXPath(); 		
		
		return navigator.evaluate(expr, null);
	}

}
