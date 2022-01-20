package com.aviva.ciso.sdlc.high;

import javax.servlet.http.HttpServletRequest;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class XPath_Injection {
	private String readUserGroup_Unsafe(HttpServletRequest request) 
			throws XPathExpressionException {
		String resultGroup = ""; 
		
		String userId = request.getParameter("UserID");
		String expr = "//USERS/USER[UserID/text()='" + userId + "']/GROUP/text()";
		
		XPath navigator = XPathFactory.newInstance().newXPath(); 
		resultGroup = navigator.evaluate(expr, null);		
		
		return resultGroup;
	}

}
