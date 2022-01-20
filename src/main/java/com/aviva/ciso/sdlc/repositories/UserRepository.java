package com.aviva.ciso.sdlc.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aviva.ciso.sdlc.entities.User;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate template;

	public List<User> findByUsernameSafe(String username) {
		return template.query("SELECT * FROM users WHERE username=?", new Object[] { username },
				new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> findByUsernameUnsafe(String username) {
		String query = "SELECT * FROM users WHERE username='" + username + "'";
		return template.query(query, new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getUsersInOrderUnsafe(String column) {
		String query = "SELECT * FROM users ORDER BY " + column;
		return template.query(query, new BeanPropertyRowMapper<User>(User.class));
	}

	/**
	 * In findUsernameSafe, we put a question mark '?' in the query and then specify the parameter to inject.
	 * This is because internally PreparedStatement is used (see documentation).
	 * PreparedStatement sanitizes properly parameters placed in the queries when they are specified with '?'.
	 * 
	 * In this specific method we cannot use this because PreparedStatement sanitizes parameters only,
	 * and not column or table names. So when we use SELECT, or ORDER_BY for example, we need to
	 * use a whitelist, see below. 
	 */
	// TODO: rename findAllUsersOrderByParam SEPARER USER REPOSITORY
	public List<User> getUsersInOrderSafe(String column) {
		/*** FIX ***: Validation by whitelisting */
		/**
		 * mitigation should be detected
		**/
		Map<String, String> wl = new HashMap<>();
		wl.put("email", "email");
		wl.put("username", "username");
		String columnName = wl.get(column);

		/**
		 * This also works: columnName = column == "email" ? "email" : "username"; 
		 */

		// column name is not a direct user input.
		String query = "SELECT * FROM users ORDER BY " + columnName;
		return template.query(query, new BeanPropertyRowMapper<User>(User.class));
	}

	public boolean isAdmin(String username) {
		String query = "SELECT is_admin FROM users WHERE username=?";

		List<Boolean> res = template.query(query, new Object[] { username },
				new BeanPropertyRowMapper<Boolean>(Boolean.class));

		return res != null && res.size() == 1;
	}

	public void addUser(String username) {
		String query = "INSERT INTO users (username, email, is_admin) VALUES (?,'',false)";
		template.update(query, username);
	}

	public List<User> findByEmail(String email) {
		return template.query("SELECT username FROM users WHERE email=?", new Object[] { email },
				new BeanPropertyRowMapper<User>(User.class));
	}

}