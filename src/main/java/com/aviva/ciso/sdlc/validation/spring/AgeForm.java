package com.aviva.ciso.sdlc.validation.spring;
import javax.validation.constraints.Digits;

public class AgeForm {
	
	@Digits(integer = 3, fraction = 0)
	private String age;
	
	public String getAge() {
		return this.age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
}
