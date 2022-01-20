package com.aviva.ciso.sdlc.medium;

import com.aviva.ciso.sdlc.medium.unsafeobjectbinding.Basket;
import com.aviva.ciso.sdlc.medium.unsafeobjectbinding.Item;
import com.aviva.ciso.sdlc.medium.unsafeobjectbinding.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Unsafe_Object_Binding_NOT_A_FIX {
	@Autowired
	private User user;
	@Autowired
	private Basket basket;

	/*** FIX ***/
	/**
	 * With Binder.setAllowedFields, we acknowledge that the fields in ALLOWED_FIELDS
	 * could be tampered with by the user.
	 * This would be the Spring framework's mitigation of the Unsafe Object binding vulnerability
	 * but may not be recognized as such by de scan tool.
	 */

	@InitBinder("saveItem")
	public void initializeBinder(WebDataBinder binder) {

		binder.setAllowedFields("id", "order");
	}

	/**
	 * Using Binder.setAllowedFields is supposed to make you think about the contents of each of these fields,
	 * and make sure that you can trust the information inside.
	 */
	@RequestMapping(value = "Unsafe_Object_Binding/Safe/", method = RequestMethod.POST)
	public String saveItem(@ModelAttribute("item") Item item) {
		basket.addItem(item);
		return "itemSavedView";
	}
}
