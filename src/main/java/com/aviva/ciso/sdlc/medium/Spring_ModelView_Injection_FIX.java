package com.aviva.ciso.sdlc.medium;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class Spring_ModelView_Injection_FIX {
	@GetMapping("Spring_ModelView_Injection/Safe/Switch")
	public ModelAndView redirect(@RequestParam("inject") String target) {
		/*** FIX ***/
		switch (target) {
		case "safe":
			return new ModelAndView("safe");
		case "safe_session":
			return new ModelAndView("safe_session");
		default:
			return new ModelAndView("error");
		}
	}
}
