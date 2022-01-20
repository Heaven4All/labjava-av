package com.aviva.ciso.sdlc.medium;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * The ModelAndView constructor takes unsanitized input.
 */
public class Spring_ModelView_Injection {
	@GetMapping("Spring_ModelView_Injection/Unsafe/")
	public ModelAndView redirect(@RequestParam("inject") String target) {
		return new ModelAndView(target);
	}
}
