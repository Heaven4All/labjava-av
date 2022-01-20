package com.aviva.ciso.sdlc.medium;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * ---------------------------------
 * Absolute_Path_Traversal:
 * ---------------------------------
 * 
 * The application opens a file based on unsanitized and unvalidated user input.
 * 
 */

public class Absolute_Path_Traversal_FIX_ESAPI {

	@GetMapping("Absolute_Path_Traversal/safe/")
	protected void getFileContents(HttpServletRequest request) throws ValidationException {
		String filename = request.getParameter("filename"); // The example will still generate an Input_Path_Not_Canonicalized vulnerability. See corresponding method for solution.
		String safeFileName = ESAPI.validator().getValidFileName("File name", filename, null, false);
		
		File file = new File(safeFileName);

		// get and return content here.
	}
}
