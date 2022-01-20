package com.aviva.ciso.sdlc.medium;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class Unvalidated_SSL_Certificate_Hostname {
	private class TestHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
}
