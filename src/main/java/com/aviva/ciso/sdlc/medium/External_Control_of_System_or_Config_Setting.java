package com.aviva.ciso.sdlc.medium;

import java.util.Properties;

import org.springframework.web.bind.annotation.RequestParam;

public class External_Control_of_System_or_Config_Setting {

	public String setConfig(@RequestParam("inject") String inject) {
		Properties props = System.getProperties();
		props.setProperty("dbUrl", inject);
		return "";
	}
}
