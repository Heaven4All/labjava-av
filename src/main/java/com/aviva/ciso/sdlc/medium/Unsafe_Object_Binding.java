package com.aviva.ciso.sdlc.medium;

import com.aviva.ciso.sdlc.medium.unsafeobjectbinding.Basket;
import com.aviva.ciso.sdlc.medium.unsafeobjectbinding.Item;
import com.aviva.ciso.sdlc.medium.unsafeobjectbinding.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Here we can see that 'saveItem' uses mass assignation by taking an item as parameter.
 * The Item object contains a "buyer" attribute of the custom type User and accessible through a public setter.
 * This should triggers the Unsafe_Object_Binding alert.
 */
@Controller
public class Unsafe_Object_Binding {
	@Autowired
	private User user;
	@Autowired
	private Basket basket;

	@RequestMapping(value = "Unsafe_Object_Binding/Unsafe/", method = RequestMethod.POST)
	public String saveItem(@ModelAttribute("item") Item item) {
		basket.addItem(item); //If the parameter "user.password=hacked!!" is added, the password for the user is changed to "hacked!!".
		return "ItemSavedView";
	}
}
