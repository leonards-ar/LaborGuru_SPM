package com.laborguru.service.user.dao;

import com.laborguru.model.User;

/**
 * Dao User interface
 * @author cnunez
 *
 */
public interface UserDao {
	User getUserByUsername(User user);
}
