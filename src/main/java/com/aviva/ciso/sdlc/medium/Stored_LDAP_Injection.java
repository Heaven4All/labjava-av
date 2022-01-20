package com.aviva.ciso.sdlc.medium;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.aviva.ciso.sdlc.repositories.UserRepository;

public class Stored_LDAP_Injection {
	@Autowired
	UserRepository users;
	
	private boolean checkUserIsInGroup_Unsafe(HttpServletRequest request) 
			throws NamingException {
		boolean isInGroup = false;  
		
		String userName = users.findByUsernameSafe("test").get(0).getUsername();
		
		/*** XXX ***/
		String userFilter = String.format("(&(objectCategory=user)(CN=%s)(memberOf=%s))", userName, "APPADMIN_GROUP_DN");
		
		// Initialize LdapContext
		DirContext ctx = null;
		
		NamingEnumeration<?> found = ctx.search("search", userFilter, null);
		// use search results

		ctx.close();
				
		return isInGroup;
	}

}
