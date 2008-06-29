/*
 * File name: SpmInterceptor.java
 * Creation date: 22/06/2008 15:01:50
 * Copyright Mindpool
 */
package com.laborguru.frontend.struts2;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.jaas.JaasAuthenticationToken;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.HttpRequestConstants;
import com.laborguru.model.Employee;
import com.laborguru.model.Menu;
import com.laborguru.model.User;
import com.laborguru.service.menu.MenuService;
import com.laborguru.service.security.UserDetailsImpl;
import com.laborguru.service.user.UserService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * This class represents the ad-hoc SPM interceptor. It will handle session
 * management and changes to the selected menu option.
 * 
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
public class SpmInterceptor implements Interceptor {
	private UserService userService;
	private MenuService menuService;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2364149684729327621L;

	/**
	 * 
	 */
	public SpmInterceptor() {
	}

	/**
	 * 
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	public void destroy() {
	}

	/**
	 * Initialize the interceptor
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	public void init() {
		//:TODO: Retrieve factory name and service names from configuration
		BeanFactory bf = ContextSingletonBeanFactoryLocator.getInstance().useBeanFactory("spm").getFactory();
		
		setUserService((UserService)bf.getBean("userService"));
		setMenuService((MenuService)bf.getBean("menuService"));
		
	}

	/**
	 * Prepares the session as needed by SPM application. This is, set the logged
	 * user in the session, the menu and the current store, among others.
	 * @param invocation
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		Map params = invocation.getInvocationContext().getParameters();

		User user = (User) session.get(HttpRequestConstants.USER);
		Object principal = retrievePrincipal();
		
		if(user == null && principal != null && principal instanceof UserDetailsImpl) {
			user = ((UserDetailsImpl)principal).getUser();
			session.put(HttpRequestConstants.USER, user);
		} else if(user == null && principal != null && principal instanceof String && !"anonymousUser".equalsIgnoreCase((String) principal)) {
			user = new User();
			user.setUserName((String)principal);
			user = getUserService().getUserByUserName(user);
			session.put(HttpRequestConstants.USER, user);
			if(user instanceof Employee) {
				session.put(HttpRequestConstants.STORE, ((Employee)user).getStore());
			}
		} else if(user == null && principal == null) {
			/*
			 * No logged in user. Just see why ACEGI is not taking this
			 * into account. Redirect to login
			 */
			return SpmActionResult.LOGIN.getResult();
		}

		Menu menu = (Menu) session.get(HttpRequestConstants.MENU);
		if(menu == null && user != null) {
			menu = getMenuService().getMenuFor(user);
			session.put(HttpRequestConstants.MENU, menu);
		}

		Object idxObj = params.get(HttpRequestConstants.MENU_ITEM_INDEX);
		if(	idxObj != null && menu != null) {
			try {
				String idx = (idxObj instanceof String[]) ? ((String[])idxObj)[0] : idxObj.toString();
				menu.setSelectedItemIndex(Integer.parseInt(idx));
			} catch(Throwable ex) {
				// Invalid index! Someone is trying to hack us?
			}
		}
		
		return invocation.invoke();
	}

	/**
	 * Retrieves the principal from the ACEGI context
	 * @return
	 */
	private Object retrievePrincipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = null;
		
		if(auth instanceof JaasAuthenticationToken) {
			JaasAuthenticationToken jaasAuth = (JaasAuthenticationToken) auth;
			Set<Principal> principals = jaasAuth.getLoginContext().getSubject().getPrincipals();
			principal = principals != null && !principals.isEmpty() ? principals.iterator().next() : null;
		}
		
		if(principal == null) {
			principal = auth.getPrincipal();
		}
		
		return principal;
	}
	
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the menuService
	 */
	public MenuService getMenuService() {
		return menuService;
	}

	/**
	 * @param menuService
	 *            the menuService to set
	 */
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
