package com.aviva.ciso.sdlc.medium;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.aviva.ciso.sdlc.medium.unsafeobjectbinding.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.aviva.ciso.sdlc.medium.unsafeobjectbinding.Basket;
import com.aviva.ciso.sdlc.repositories.ItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * We use a potentially dangerous information to determine the number of iterations of a loop.
 * A user could make the application do a very long number of iterations and block or crash it.
 */
public class Unchecked_Input_for_Loop_Condition_FIX {

	@Autowired
	ItemRepository itemRepository;
	
	/**
	 * You have to make sure the maximum number of iterations is reasonable before entering the loop.
	 * This limit should be, when possible, between 0 and 10 000, otherwise the mitigation may stay undetected.
	 */
	@InitBinder("getPrice")
	@PostMapping("Uncheck_Input_For_Loop_Condition/Safe/")
	public String getPrice(HttpServletRequest request) throws JsonProcessingException {
		
		String json = request.getParameter("basket");
		Basket basket = (new ObjectMapper()).readValue(json, Basket.class);
		
		List<Item> items = basket.getItems();

		Float total = 0f;
		
		/*** FIX: the number of iterations should be < 10 000.
		 * 		NOTE: make sure items.size() is put in a variable
		 ***/
		int itemsSize = items.size();
		if (itemsSize > 5000) {
			return "You have too much items in your basket.";
		}
		
		for (int i = 0; i < itemsSize; i++) {
			total += itemRepository.getPriceItem(items.get(i).getId());
		}

		return "You have to pay " + total.toString() + " euros.";
	}
}
