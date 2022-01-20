package com.aviva.ciso.sdlc.medium;

/**
 * The scan tool may signal false positives for this if it cannot
 * check if the loaded classes come from external sources and if they were
 * checked before.
 * 
 * FALSE POSITIVE may arise, except:
 * - if the loaded class comes from the filesystem or the network
 * 	 (load an external JAR file). In this case you need to compute
 * 	 the hash of the file on a trusted machine and then compare this hash
 *   with another hash computed at each loading of external classes.
 */
public class Download_Of_Code_Without_Integrity_Check {
	
	
	public void importClass() {
		System.loadLibrary("https://example.com/com.example.package");
	}

}
