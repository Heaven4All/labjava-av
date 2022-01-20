package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Improper_Restriction_Of_XXE_Ref {

	/**
	 * It is yet again a problem of sanitization of data. Here we take the request
	 * parameter "xmlInput" without verifying that it is safe.
	 * @throws XMLStreamException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, XMLStreamException {

		String strContent = request.getParameter("xmlInput");
		PrintWriter out = response.getWriter();
		Reader reader = new StringReader(strContent);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader xmlReader = null;

		xmlReader = factory.createXMLStreamReader(reader);
		out.close();
		if (xmlReader != null) {
			xmlReader.close();
		}
		reader.close();
	}

}
