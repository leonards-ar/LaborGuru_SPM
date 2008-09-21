package com.laborguru.service.user;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.User;
import com.laborguru.service.user.dao.UserDao;

/**
 * Spring Implementation for UserService
 * @author cnunez
 *
 */
public class UserServiceBean implements UserService {
	private static final Logger log = Logger.getLogger(UserServiceBean.class);
	
	public static final String USER_NULL = "The user passed as parameter cannot be can not be null";
	public static final String USER_NAME_NULL = "The user passed as parameter cannot have null username";
	
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.laborguru.service.user.UserService#save(com.laborguru.model.User)
	 */
	public User save(User user) throws SpmCheckedException{
		
		User retUser = null;
		
		if(user == null) 
		{
			log.error("Employee passed in as parameter is null");
			throw new IllegalArgumentException("Employee passed in as parameter is null");				
		}
		
		if(log.isDebugEnabled()){
			log.debug("before save employee:"+ user);
		}

		retUser = user.getId()!= null? update(user):create(user);
		
		if(log.isDebugEnabled()){
			log.debug("after save employee:"+ user);
		}
		
		return retUser;
	}
	

	/**
	 * @param userLoggingOn
	 * @return
	 */
	public User getUserByUserName(User userLoggingOn) {
		if (userLoggingOn == null)
			throw new IllegalArgumentException(USER_NULL);
		
		if (userLoggingOn.getUserName() == null)
			throw new IllegalArgumentException(USER_NAME_NULL);
		
				
		return userDao.getUserByUsername(userLoggingOn);
	}
	
	public List<User> findAll(){
		return userDao.findAll();
	}

	
	public User getUserById(User user) {
		return this.userDao.getUserById(user);
	}
	
	
	/**
	 * Creates employee
	 */
	private User create(User user) throws SpmCheckedException {		
							
		//Checking if user name already exist
		if (userDao.existUser(user.getUserName()))
		{
			String exMgs = "username: "+ user.getUserName()+" already exist in the database";
			log.error(exMgs);
			throw new SpmCheckedException(exMgs, ErrorEnum.USERNAME_ALREADY_EXIST_ERROR, new String[]{user.getUserName()});
		}
		user.setCreationDate(new Date());
		return  userDao.save(user);
	}

	/**
	 * Updates employee
	 */
	private User update(User user) throws SpmCheckedException {
							
		//Checking if user name already exist
		User auxUser = userDao.getUserByUsername(user);
		
		if ((auxUser != null) && !auxUser.getId().equals(user.getId()))
		{
			String exMgs = "username: "+ user.getUserName()+" already exist in the database";
			log.error(exMgs);
			throw new SpmCheckedException(exMgs, ErrorEnum.USERNAME_ALREADY_EXIST_ERROR, new String[]{user.getUserName()});
			
		}
		
		//Evicting the user
		userDao.evict(auxUser);
		
		user.setLastUpdateDate(new Date());
		return userDao.save(user);
	}
	
	/**
	 * Deletes a user.
	 * @param user
	 * @see com.laborguru.service.user.UserService#delete(com.laborguru.model.User)
	 */
	public void delete(User user) {
		userDao.delete(user);
	}
	
	/* (non-Javadoc)
	 * @see com.laborguru.service.user.UserService#setUserDao(com.laborguru.service.user.dao.UserDao)
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

}
