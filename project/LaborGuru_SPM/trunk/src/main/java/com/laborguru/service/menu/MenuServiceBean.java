/**
 * 
 */
package com.laborguru.service.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.laborguru.model.Menu;
import com.laborguru.model.MenuItem;
import com.laborguru.model.Profile;
import com.laborguru.model.User;
import com.laborguru.service.menu.dao.MenuDao;

/**
 * @author Mariano
 *
 */
public class MenuServiceBean implements MenuService {
	private static final Map<Profile, Menu> MENU_CACHE = new HashMap<Profile, Menu>();
	
	private MenuDao menuDao;

	/**
	 * 
	 */
	public MenuServiceBean() {
		
	}
	
	/**
	 * @return the dao
	 */
	public MenuDao getMenuDao() {
		return menuDao;
	}

	/**
	 * @param menuDao the dao to set
	 */
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	/**
	 * @see com.laborguru.service.menu.MenuService#getMenuFor(com.laborguru.model.User)
	 */
	public Menu getMenuFor(User user) {
		/*
		 * :TODO: Must support multiple profiles per user!
		 */
		Menu menu = MENU_CACHE.get(user.getProfiles().iterator().next());
		if(menu == null) {
			List<MenuItem> completeMenu = getMenuDao().getMenu();
			removeNotAllowedMenuItems(completeMenu, user.getProfiles());
			menu = new Menu();
			menu.setItems(completeMenu);
		}
		return menu;
	}

	/**
	 * 
	 * @param menuItems
	 */
	private void removeNotAllowedMenuItems(List<MenuItem> menuItems, Set<Profile> userProfiles) {
		if(menuItems != null) {
			for(MenuItem item : menuItems) {
				boolean hasPermission = false;
				for(Profile profile : userProfiles) {
					if(profile.hasPermission(item.getPermission())) {
						hasPermission = true;
						break;
					}
				}
				if(!hasPermission) {
					menuItems.remove(item);
				}
				removeNotAllowedMenuItems(item.getOrderedChildMenuItems(), userProfiles);
			}
		}
	}
}
