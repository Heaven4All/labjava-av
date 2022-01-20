package com.aviva.ciso.sdlc.medium;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;

public class Inadequate_Encryption_Strength_FIX {
	@GetMapping("Inadequate_Encryption_Strength/Unsafe/")
	String protectSSN(HttpServletRequest req) {
		String socialSecurityNum = req.getParameter("SocialSecurityNo");
		
		return DigestUtils.sha256Hex(socialSecurityNum);
	}
}
