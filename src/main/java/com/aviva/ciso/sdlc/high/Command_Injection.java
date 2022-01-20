package com.aviva.ciso.sdlc.high;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class Command_Injection {
	private void executeSystemCommand_Unsafe(HttpServletRequest request) throws IOException {
		String userCommand = request.getParameter("cmd");

		Runtime runtime = Runtime.getRuntime();
		Process subProc = runtime.exec("cmd.exe /c '" + userCommand + "'");
	}
}
