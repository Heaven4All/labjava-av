package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

/**
 * The scan tool may signal false positives for this because it cannot
 * check if the loaded classes come from external sources and if they were
 * checked before.
 * 
 * FALSE POSITIVE most of the time, except:
 * - if the loaded class comes from the filesystem or the network
 * 	 (load an external JAR file). In this case you need to compute
 * 	 the hash of the file on a trusted machine and then compare this hash
 *   with another hash computed at each loading of external classes.
 */
public class Download_Of_Code_Without_Integrity_Check_FIX {
	
	
	private static final Object TRUSTED_HASH_OF_FILE = "Put hash of trusted file here.";

	private String computeHash(String file) throws NoSuchAlgorithmException, IOException {
		InputStream is = Files.newInputStream(Paths.get(file));
		return org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
	}
	
	public void importClass() throws NoSuchAlgorithmException, IOException {
		
		String lib = "https://example.com/com.example.package";
		String hashOfFile = computeHash(lib);
		
		if (hashOfFile.equals(TRUSTED_HASH_OF_FILE)) {
			System.loadLibrary(lib);
		}
	}

}
