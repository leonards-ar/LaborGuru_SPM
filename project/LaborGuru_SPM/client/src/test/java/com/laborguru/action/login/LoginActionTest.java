package com.laborguru.action.login;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.laborguru.action.login.LoginAction;
import com.laborguru.model.User;
import com.opensymphony.xwork2.Action;

/**
 * Test Case for LoginAction
 * @author cnunez
 *
 */
public class LoginActionTest {

	@Test
	public void execute_HappyPath() throws Exception{
		LoginAction loginAction = new LoginAction();
		assertEquals(Action.SUCCESS, loginAction.execute());
	}
	
	@Test
	//TODO
	public void execute_InvalidaUser() throws Exception{
		LoginAction loginAction = new LoginAction();
		User user = new User();
		user.setPassword("password");
		
		loginAction.setUser(user);
		assertEquals(Action.SUCCESS, loginAction.execute());
	}
}
