package com.laborguru.model;

import java.util.Date;

public class Employee extends User {

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private Store store;
	private Position defaultPosition;
	private boolean manager;
	private String mobilePhone;
	private String homePhone;
	private Date hireDate;
	private int maxHoursWeek;
	private int maxDaysWeek;
	private int maxHoursDay;
	private String address;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String comments;
	
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public boolean isManager() {
		return manager;
	}
	public void setManager(boolean manager) {
		this.manager = manager;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public int getMaxHoursWeek() {
		return maxHoursWeek;
	}
	public void setMaxHoursWeek(int maxHoursWeek) {
		this.maxHoursWeek = maxHoursWeek;
	}
	public int getMaxDaysWeek() {
		return maxDaysWeek;
	}
	public void setMaxDaysWeek(int maxDaysWeek) {
		this.maxDaysWeek = maxDaysWeek;
	}
	public int getMaxHoursDay() {
		return maxHoursDay;
	}
	public void setMaxHoursDay(int maxHoursDay) {
		this.maxHoursDay = maxHoursDay;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the defaultPosition
	 */
	public Position getDefaultPosition() {
		return defaultPosition;
	}
	/**
	 * @param defaultPosition the defaultPosition to set
	 */
	public void setDefaultPosition(Position defaultPosition) {
		this.defaultPosition = defaultPosition;
	}
	
	

}
