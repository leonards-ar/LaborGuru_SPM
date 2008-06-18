package com.laborguru.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Spm User Type
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class User extends SpmObject {

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String userName;
	private String password;
	private Integer status;
	private String email;
	private String name;
	private String surname;
	private Date lastLogon;
	private Integer loginCount;
	private Date creationDate;
	private Date lastUpdateDate;
	private Set<Profile> profiles;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * @return
	 */
	public Set<Profile> getProfiles() {
		return profiles;
	}
	
	
	/**
	 * We leave it private to enforce the cardinality with the addProfile.
	 * DO NOT MAKE IT PUBLIC
	 * @param profile
	 */
	private void setProfiles(Set<Profile> profile) {
		this.profiles = profile;
	}
	
	/**
	 * @param profile
	 */
	public void addProfile(Profile profile){
		
		if (profile == null){
			throw new IllegalArgumentException("Null profile passed in as parameter");
		}
		
		if(this.profiles == null) {
			this.profiles = new HashSet<Profile>();
		}

		this.profiles.add(profile);
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the lastLogon
	 */
	public Date getLastLogon() {
		return lastLogon;
	}
	/**
	 * @param lastLogon the lastLogon to set
	 */
	public void setLastLogon(Date lastLogon) {
		this.lastLogon = lastLogon;
	}
	/**
	 * @return the loginCount
	 */
	public Integer getLoginCount() {
		return loginCount;
	}
	/**
	 * @param loginCount the loginCount to set
	 */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFullName() {
		StringBuffer fullName = new StringBuffer();
		if(getName() != null) {
			fullName.append(getName());
		}
		fullName.append(" ");
		if(getSurname() != null) {
			fullName.append(getSurname());
		}
		return fullName.toString().trim();
	}
}
