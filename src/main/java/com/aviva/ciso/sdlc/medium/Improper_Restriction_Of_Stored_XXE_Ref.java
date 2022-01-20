package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.springframework.beans.factory.annotation.Autowired;

import com.aviva.ciso.sdlc.entities.User;
import com.aviva.ciso.sdlc.repositories.UserRepository;

/**
 * There is no real difference with Improper_Restriction_Of_XXE_Ref
 * except that in this case the data comes from a database, so it is 'stored'
 * 
 * NOTE: This should be included in Improper_Restriction_Of_XXE_Ref:
 * 		 for every Improper_Restriction_Of_Stored_XXE_Ref there is one more
 * 		 Improper_Restriction_Of_XXE_Ref => redundancy ?
 */
public class Improper_Restriction_Of_Stored_XXE_Ref {

	@Autowired
	private UserRepository userRepository;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<User> user = userRepository.findByUsernameSafe(request.getParameter("username"));
		String strContent = user.get(0).getEmail();

		PrintWriter out = response.getWriter();
		Reader reader = new StringReader(strContent);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader xmlReader = null;

		try {
			xmlReader = factory.createXMLStreamReader(reader);
		} catch (Exception e) {
			// handle exception
		} finally {
			try {
				out.close();
				if (xmlReader != null) {
					xmlReader.close();
				}
				reader.close();
			} catch (Exception ex) {
				// handle exception
			}
		}
	}

}
