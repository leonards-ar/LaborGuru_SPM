package com.laborguru.action.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Profile;
import com.laborguru.model.User;
import com.laborguru.model.filter.SearchUserFilter;
import com.laborguru.service.data.ReferenceDataService;
import com.laborguru.service.profile.ProfileService;
import com.laborguru.service.user.UserService;
import com.opensymphony.xwork2.Preparable;


/**
 * CRUD for users that are not employees
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class UserPrepareAction extends SpmAction implements Preparable{

	private Integer userId;
	private User user;
	private List<User> users;
	

	
	private List<Profile> profiles;
	private Map<String, String> statusMap;
	
	private SearchUserFilter searchUser;

	private UserService userService;
	private ProfileService profileService;
	private ReferenceDataService referenceDataService;
	
	private Profile userProfile;
	private String passwordConfirmation;
	private boolean removePage;
	
	/**
	 * Prepare the data to be used on the edit page
	 * Loads position and status list
	 * @throws Exception
	 */
	public void prepareEdit() {
		loadListsForAddEditPage();
	}

	/**
	 * Prepare data to be used to display employee data
	 */
	public void prepareShow() {
		loadListsForAddEditPage();
	}
	
	/**
	 * Prepare data to be used to display employee data
	 * before removal.
	 */
	public void prepareRemove() {
		loadListsForAddEditPage();
	}
	
	/**
	 * Prepare the data to be used on the add page
	 * Loads position and status list
	 * @throws Exception
	 */
	public void prepareAdd(){
		loadListsForAddEditPage();
	}
	
	/**
	 * Prepare the data to be used on the add page
	 * We should preload the list needed to render the add page.
	 * When a validation fails the application goes back to the add page and this data is needed.
	 * Loads position and status list
	 * @throws Exception
	 */
	public void prepareSave(){
		loadListsForAddEditPage();
	}
	
	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		//It's needed by the Preparable interface, don't comment out or removed
	}
	
	public String edit() throws Exception {
		loadUserById();
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * Retrieves all the employees that belong to the user's store.
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		setUsers(userService.findAll());
		return SpmActionResult.LIST.getResult();
	}
	
	
	public String remove() throws Exception {
		loadUserById();
		setRemovePage(true);
		return SpmActionResult.SHOW.getResult();
	}
	
	public String delete() throws Exception {		
		//Getting user
		User auxUser = userService.getUserById(getUser());		
		userService.delete(auxUser);
		
		return SpmActionResult.LISTACTION.getResult();
	}
	
	public String add() throws Exception {
		return SpmActionResult.EDIT.getResult();
	}
	
	public String show() throws Exception {
		loadUserById();
		return SpmActionResult.SHOW.getResult();
	}
	
	
	public String search() throws Exception {
		setUsers(userService.filterUser(getSearchUser()));
		return SpmActionResult.LIST.getResult();
	}
	
	public String save() throws Exception {
		try {
			getUser().setProfile(getUserProfile());
			userService.save(getUser());
			return SpmActionResult.LISTACTION.getResult();
			
		} catch (SpmCheckedException e) {
			addActionError(e.getErrorMessage());
		}
		
		return SpmActionResult.EDIT.getResult();
	}
	
	private void loadListsForAddEditPage() {
		setProfiles(getProfileService().findAll());
		setStatusMap(getReferenceDataService().getStatus());
	}

	private void loadUserById() {
		User tmpUser = new User();
		tmpUser.setId(userId);
		setUser(userService.getUserById(tmpUser));
		setPasswordConfirmation(getUser().getPassword());
		setUserProfile(getUser().getProfile());
	}
	
	private Profile getProfileById(Integer id) {
		for(Profile profile: getProfiles()) {
			if(profile.getId().equals(id)){
				return profile;
			}
		}
		return null;
	}
	
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the profiles
	 */
	public List<Profile> getProfiles() {
		return profiles;
	}

	/**
	 * @param profiles the profiles to set
	 */
	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	/**
	 * @return the statusMap
	 */
	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	/**
	 * @param statusMap the statusMap to set
	 */
	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}

	/**
	 * @return the searchUser
	 */
	public SearchUserFilter getSearchUser() {
		return searchUser;
	}

	/**
	 * @param searchUser the searchUser to set
	 */
	public void setSearchUser(SearchUserFilter searchUser) {
		this.searchUser = searchUser;
	}

	/**
	 * @return the profileService
	 */
	public ProfileService getProfileService() {
		return profileService;
	}

	/**
	 * @param profileService the profileService to set
	 */
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
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
	 * @return the referenceDataService
	 */
	public ReferenceDataService getReferenceDataService() {
		return referenceDataService;
	}

	/**
	 * @param referenceDataService the referenceDataService to set
	 */
	public void setReferenceDataService(ReferenceDataService referenceDataService) {
		this.referenceDataService = referenceDataService;
	}

	/**
	 * @return the passwordConfirmation
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	/**
	 * @param passwordConfirmation the passwordConfirmation to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	/**
	 * @return the removePage
	 */
	public boolean isRemovePage() {
		return removePage;
	}

	/**
	 * @param removePage the removePage to set
	 */
	public void setRemovePage(boolean removePage) {
		this.removePage = removePage;
	}

	/**
	 * @return the userProfile
	 */
	public Profile getUserProfile() {
		return userProfile;
	}

	/**
	 * @param userProfile the userProfile to set
	 */
	public void setUserProfile(Profile userProfile) {
		this.userProfile = userProfile;
	}


}
