package com.aviva.ciso.sdlc.medium;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class Unvalidated_SSL_Certificate_Hostname_FIX {

	/*** NOTE: False positive. ***/
	private class TestHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			// FIX
			return HttpsURLConnection.getDefaultHostnameVerifier().verify(hostname, session);
		}
	}

	/*** NOTE: False positive. ***/
	private class Test2HostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			/*** FIX ***/
			return false;
		}
	}
}
