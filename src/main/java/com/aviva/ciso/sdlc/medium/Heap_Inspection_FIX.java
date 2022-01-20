package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

/**
 * If someone has access to the application's memory, it will be much harder for them
 * to access 'password' if it is stored in a SealedObject, because it is encrypted in memory.
 */
public class Heap_Inspection_FIX {
	private String encryptionAlgorithm = "AES/CBC/PKCS5Padding";
	private SealedObject password;
	private KeyGenerator keyGen;
	private SecretKey key = keyGen.generateKey();

	public void setPassword(Character[] password) throws NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, IOException, InvalidKeyException {
		Cipher c = Cipher.getInstance(encryptionAlgorithm);
		c.init(Cipher.ENCRYPT_MODE, key);
		List<Character> characterList = Arrays.asList(password);
		this.password = new SealedObject((Serializable) characterList, c);

		// Zero out input. This will also overwrite the values in characterList by reference.
		// This may be useless, see https://security.stackexchange.com/questions/74718/is-it-more-secure-to-overwrite-the-value-char-in-a-string/75891#75891
		Arrays.fill(password, '\0');
	}

	public SealedObject getPassword() {
		return password;
	}

	public void setPassword(SealedObject password) {
		this.password = password;
	}
}
