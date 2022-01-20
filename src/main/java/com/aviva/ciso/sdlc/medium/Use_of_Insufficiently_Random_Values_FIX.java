package com.aviva.ciso.sdlc.medium;

import java.security.SecureRandom;

import org.springframework.web.bind.annotation.GetMapping;

public class Use_of_Insufficiently_Random_Values_FIX {
	/*** FIX ***/
	private SecureRandom random;

	@GetMapping("Use_of_Insufficiently_Random_Values/Safe/")
	public String safeRandom() {
		return "Secret number: " + random.nextInt();
	}
}
