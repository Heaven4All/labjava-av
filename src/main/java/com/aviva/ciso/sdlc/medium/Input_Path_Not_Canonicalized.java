package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * We return the content of a file without sanitization or
 * validation of the path.
 * Search local file inclusion(LFI) and remote file inclusion(RFI) for
 * an example of exploitation in the wild.
 */
public class Input_Path_Not_Canonicalized {

	private static final String SERVED_FILES_DIR = "include/";

	@GetMapping(value = "Input_Path_Not_Canonicalized/Unsafe/")
	public String getFileContents(HttpServletRequest request) throws IOException {
		String filename = request.getParameter("filename");
		Path path = Paths.get(SERVED_FILES_DIR, filename);
		return Arrays.toString(Files.readAllBytes(path));
	}
}
