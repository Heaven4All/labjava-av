package com.aviva.ciso.sdlc.high;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

import com.aviva.ciso.sdlc.entities.User;

public class Deserialisation_of_Untrusted_Data_FIX {
	@GetMapping("Parameter_Tempering/Unsafe/")
	public void test(HttpServletRequest request) throws IOException, ClassNotFoundException {
		// Secure Stream reading code with SafeObjectInputStream
		ServletInputStream sis = request.getInputStream();
		ObjectInputStream oins = new SafeObjectInputStream(sis);
		User obj = (User)oins.readObject();
	}

	private class SafeObjectInputStream extends ObjectInputStream {
		public SafeObjectInputStream(InputStream in) throws IOException {
			super(in);
		}

		@Override
	    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
			if (!desc.getName().equals(User.class.getName())) {
				throw new ClassNotFoundException("Unsupported Class: ");
	        }
			return super.resolveClass(desc);
	    }
	}
}
