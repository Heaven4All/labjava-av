package com.aviva.ciso.sdlc.high;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI;

public class LDAP_Injection_FIX {
	private boolean checkUserIsInGroup_Unsafe(HttpServletRequest request) throws NamingException {
		boolean isInGroup = false;  
		
		String userName = request.getParameter("UserName");
		
		/*** FIX: sanitize userName ***/
		userName = ESAPI.encoder().encodeForLDAP(userName);

		String userFilter = String.format("(&(objectCategory=user)(CN=%s)(memberOf=%s))", userName, "APPADMIN_GROUP_DN");

		// Initialize LdapContext
		DirContext ctx = null;

		NamingEnumeration<?> found = ctx.search("search", userFilter, null);
		// use search results

		ctx.close();
				
		return isInGroup;
	}

}
