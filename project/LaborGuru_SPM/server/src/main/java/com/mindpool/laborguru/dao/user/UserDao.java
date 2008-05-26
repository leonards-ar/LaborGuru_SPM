package com.mindpool.laborguru.dao.user;

import com.mindpool.laborguru.model.User;

/**
 * Dao User interface
 * @author cnunez
 *
 */
public interface UserDao {
	User getUserByUsername(User user);
}
