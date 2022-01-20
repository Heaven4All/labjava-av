package com.aviva.ciso.sdlc.medium;

import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Input_Path_Not_Canonicalized_FIX {

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

	@GetMapping(value = "Input_Path_Not_Canonicalized/Safe/")
	public String getFileContents(HttpServletRequest request) throws IOException {
		String unsafeFilename = request.getParameter("filename");

		/*** FIX:  ***/
		/*** NOTE: This to avoid both an Absolute_Path_Traversal (Medium) and a Relative_Path_Traversal (Low) vulnerability ***/
		String safeFilename = sanitizePathTraversal(unsafeFilename);
		Path safePath = Paths.get(SERVED_FILES_DIR, safeFilename);

		/***
		 * To avoid an Input_Path_Not_Canonicalized vulnerability we need to check that the sanitized path is indeed the expected
		 * normalized canonical path.
		 * ***/
		Path canonicalizedPath = Paths.get(SERVED_FILES_DIR, unsafeFilename).normalize();
		if (!safePath.equals(canonicalizedPath)) {
			//do some error handling
			throw new IOException("Invalid filename.");
		}
		/*** END FIX ***/

		return Arrays.toString(Files.readAllBytes(canonicalizedPath));
	}
}
