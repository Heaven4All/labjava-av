package com.aviva.ciso.sdlc.medium;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class Use_of_Cryptographically_Weak_PRNG_FIX {
	/*** FIX ***/
	private SecureRandom random;
	private byte[] salt = new byte[16];

	@GetMapping("Use_of_Cryptographically_Weak_PRNG/Safe/")
	public byte[] hashPassword(@RequestParam("password") String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		random.nextBytes(salt);
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		return factory.generateSecret(spec).getEncoded();
	}
}
