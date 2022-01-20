package com.aviva.ciso.sdlc.medium;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Using a hash function to store sensitive information without salt is dangerous
 * because if someone gains acces to hashes and has also access to other databases
 * with hash <-> plaintext equivalent, they could crack some hashes.
 * See rainbow tables attack.
 *
 */
public class Use_of_a_One_Way_Hash_without_a_Salt {

	@GetMapping("Use_of_a_One_Way_Hash_without_a_Salt/Unsafe/")
	public String hashPassword(@RequestParam("password") String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] data = password.getBytes();
	  	byte[] hash = null; 
	  
	  	MessageDigest md = MessageDigest.getInstance("MD5");
	  	hash = md.digest(data);
	  
	  	return Base64.getEncoder().encodeToString(hash); 
	}
}
