package com.aviva.ciso.sdlc.medium;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ---------------------------------
 * Absolute_Path_Traversal:
 * ---------------------------------
 * 
 * The application opens a file based on unsanitized and unvalidated user input.
 * 
 */

public class Absolute_Path_Traversal_FIX {
	private static final String SERVED_FILES_DIR = "include/";

	/**
	 * Ensures access only to files in a given folder, no traversal
	 * @param filename
	 * @return
	 */
	private static String sanitizePathTraversal(String filename) {
		Path p = Paths.get(filename);
		return p.getFileName().toString();
	}

	@GetMapping("Absolute_Path_Traversal/safe/")
	private String getFileContents(HttpServletRequest request) throws IOException {
		/*** FIX:  ***/
		String safeFilename = sanitizePathTraversal(request.getParameter("filename")); // The example will still generate an Input_Path_Not_Canonicalized vulnerability. See corresponding method for solution.

		/*** END FIX ***/

		Path path = Paths.get(SERVED_FILES_DIR, safeFilename);
		byte[] fileContentBytes = Files.readAllBytes(path);
		String fileContents = new String(fileContentBytes);
		return fileContents;
	}
}
