package com.aviva.ciso.sdlc.medium;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * ---------------------------------
 * Absolute_Path_Traversal:
 * ---------------------------------
 *
 * The application opens a file based on unsanitized and unvalidated user input.
 */

public class Absolute_Path_Traversal {
	
	@GetMapping("Absolute_Path_Traversal/Unsafe/")
	public void getFileNameContents(HttpServletRequest request) {
		String filename = request.getParameter("filename");
		File file = new File(filename);

		// get and return content here.
	}
}
