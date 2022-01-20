package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletRequest;

public class DoS_By_Sleep_FIX {
	public byte[] getBytesFromInputStream(InputStream is, ServletRequest request) throws IOException {
		int length = Integer.max(Integer.parseInt(request.getParameter("length")), 100_000);
		
		byte[] bytes = new byte[length];

		is.read(bytes, 0, length);
		is.close();
		return bytes;
	}
}
