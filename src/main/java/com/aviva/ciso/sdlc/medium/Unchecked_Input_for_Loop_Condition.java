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
 * 
 */
public class Unchecked_Input_for_Loop_Condition {

	@Autowired
	private ItemRepository itemRepository;

	@InitBinder("getPrice")
	@PostMapping("Unchecked_Input_For_Loop_Condition/Unsafe/")
	public String getPrice(HttpServletRequest request) throws JsonProcessingException {
		
		String json = request.getParameter("basket");
		Basket basket = (new ObjectMapper()).readValue(json, Basket.class);
		
		List<Item> items = basket.getItems();

		Float total = 0f;

		for (int i = 0; i < items.size(); i++) {
			total += itemRepository.getPriceItem(items.get(i).getId());
		}

		return "You have to pay " + total.toString() + " euros.";
	}
}
