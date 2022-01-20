package com.aviva.ciso.sdlc.high;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

import com.aviva.ciso.sdlc.entities.User;

public class Deserialisation_of_Untrusted_Data {
	@GetMapping("Deserialisation_of_Untrusted_Data/Unsafe/")
	public void test(HttpServletRequest request) throws IOException, ClassNotFoundException {
		ServletInputStream sis = request.getInputStream();
		User obj = (User)(new ObjectInputStream(sis)).readObject();
	}
}
