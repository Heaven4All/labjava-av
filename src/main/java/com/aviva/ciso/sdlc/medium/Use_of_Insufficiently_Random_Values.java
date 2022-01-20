package com.aviva.ciso.sdlc.medium;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;

public class Use_of_Insufficiently_Random_Values {
	/**
	 * Random uses the time to generate values, so the sequences are predictable and can be bruteforced.
	 * Use SecureRandom instead, as shown in the fix.
	 */
	/*** XXX ***/
	private Random random;

	@GetMapping("Use_of_Insufficiently_Random_Values/Unsafe/")
	public String unsafeRandom() {
		return "Secret number: " + random.nextInt();
	}
}
