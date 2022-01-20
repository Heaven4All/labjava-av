package com.aviva.ciso.sdlc.repositories;

import java.util.List;

import com.aviva.ciso.sdlc.medium.utils.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
	@Autowired
	private JdbcTemplate template;

	public Float getPriceItem(int id) {
		String query = "SELECT * FROM items WHERE id=?";
		List<Item> result = template.query(query, new Object[] { id },
				new BeanPropertyRowMapper<Item>(Item.class));

		// No items found with given id.
		if (result.size() != 1)
			return 0f;

		return result.get(0).getPrice();
	}

}