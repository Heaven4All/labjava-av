package com.aviva.ciso.sdlc.high;

import java.io.IOException;
import org.owasp.esapi.codecs.WindowsCodec;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI;

public class Command_Injection_FIX {
	private void executeSystemCommand_Unsafe(HttpServletRequest request) throws IOException {
		String userCommand = ESAPI.encoder().encodeForOS(new WindowsCodec(), request.getParameter("cmd"));

		Runtime runtime = Runtime.getRuntime();
		Process subProc = runtime.exec("cmd.exe /c '" + userCommand + "'");
	}
}
