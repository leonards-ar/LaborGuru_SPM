/*
 * File name: SpmFilter.java
 * Creation date: 16/06/2008 21:06:46
 * Copyright Mindpool
 */
package com.laborguru.frontend.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.laborguru.model.Menu;
import com.laborguru.model.User;
import com.laborguru.service.menu.MenuService;
import com.laborguru.service.security.UserDetailsImpl;
import com.laborguru.service.user.UserService;
import com.laborguru.service.user.UserServiceBean;
import com.mindpool.security.service.UserAuthenticationService;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class SpmFilter implements Filter {
	private UserService userService;
	private MenuService menuService;
	
	/**
	 * @return the menuService
	 */
	public MenuService getMenuService() {
		return menuService;
	}

	/**
	 * @param menuService the menuService to set
	 */
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 */
	public SpmFilter() {
	}

	/**
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Menu menu = (Menu) httpRequest.getSession().getAttribute("spmMenu");
		
		if(principal != null && principal instanceof UserDetailsImpl) {
			httpRequest.setAttribute("spmUser", ((UserDetailsImpl)principal).getUser());
		} else if(principal != null && principal instanceof String && !"anonymousUser".equalsIgnoreCase((String) principal)) {
			/*
			 * All this must be changed for some better solution!!!!!!!!!
			 * Temporary
			 */
			User user = (User) httpRequest.getSession().getAttribute("spmUser");
			if(user == null) {
				user = new User();
				user.setUserName((String)principal);
				user = getUserService().getUserByUserName(user);
				httpRequest.getSession().setAttribute("spmUser", user);
			}
			if(user != null) {
				httpRequest.setAttribute("spmUser", user); 
				
				if(menu == null) {
					menu = getMenuService().getMenuFor(user);
					httpRequest.getSession().setAttribute("spmMenu", menu);
				}
				httpRequest.setAttribute("spmMenu", menu); 
			}

		} else {
			/*
			 * What can we do here. It can be a String holding just the username
			 * or a Principal object. This should never happen?
			 */
		}
		if(httpRequest.getParameter("menuItemIndex") != null && menu != null) {
			try {
				menu.setSelectedItemIndex(Integer.parseInt(httpRequest.getParameter("menuItemIndex")));
			} catch(Throwable ex) {
				// Invalid index! Someone is trying to hack us?
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @param config
	 * @throws ServletException
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(config.getInitParameter("appContextPath"));
		setUserService((UserService)ctx.getBean("userService"));
		setMenuService((MenuService)ctx.getBean("menuService"));
	}
}
