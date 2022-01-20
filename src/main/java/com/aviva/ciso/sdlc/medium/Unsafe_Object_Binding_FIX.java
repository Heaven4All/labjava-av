package com.aviva.ciso.sdlc.medium;

import com.aviva.ciso.sdlc.medium.unsafeobjectbinding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class Unsafe_Object_Binding_FIX {
	@Autowired
	private User user;
	@Autowired
	private Basket basket;

	/*** FIX ***/
	/**
	 * We use the Data Transfer Object design pattern here to provide only what is needed through primary types (String, int, ) attributes
	 * and avoid exposing redundant sensible business data (through custom type attributes, i.e. the "buyer" attribute of class User).
	 * Avoiding using public accessible attributes of custom types is the only kind of implementation that is accepted in regard to mass assignation.
	 */
	@RequestMapping(value = "Unsafe_Object_Binding/Safe/", method = RequestMethod.POST)
	public String saveItem(@ModelAttribute("item") ItemDTO itemDTO) {
		//convert DTO object to business logic
		Item item = DTOMapper.convertFromItemDTO(itemDTO);
		item.setBuyer(user);
		basket.addItem(item);
		return "itemSavedView";
	}
}
