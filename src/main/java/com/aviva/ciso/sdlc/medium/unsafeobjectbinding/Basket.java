package com.aviva.ciso.sdlc.medium.unsafeobjectbinding;

import com.aviva.ciso.sdlc.medium.unsafeobjectbinding.Item;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Basket {
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}
	public void addItem(Item item) {
		items.add(item);
	}
}
