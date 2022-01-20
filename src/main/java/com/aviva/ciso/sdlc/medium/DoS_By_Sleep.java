package com.aviva.ciso.sdlc.medium;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletRequest;

public class DoS_By_Sleep {
	public byte[] getBytesFromInputStream(InputStream is, ServletRequest request) throws IOException {
		int length = Integer.parseInt(request.getParameter("length"));
		byte[] bytes = new byte[length];

		is.read(bytes, 0, length);
		is.close();
		return bytes;
	}
}
