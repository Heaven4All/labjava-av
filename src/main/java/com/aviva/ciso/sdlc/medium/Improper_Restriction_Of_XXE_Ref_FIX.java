package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class Improper_Restriction_Of_XXE_Ref_FIX {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String strContent = request.getParameter("xmlInput");
		PrintWriter out = response.getWriter();
		Reader reader = new StringReader(strContent);
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader xmlReader = null;

		try {
			/*** FIX: these properties make injected xml harmless. ***/
			factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
			factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);

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
