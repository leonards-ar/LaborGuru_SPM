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
	private double wage;
	private String employeeId;
	
	/**
	 * 
	 * @return
	 */
	public Store getStore() {
		return store;
	}
	
	/**
	 * 
	 * @param store
	 */
	public void setStore(Store store) {
		this.store = store;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isManager() {
		return manager;
	}
	
	/**
	 * 
	 * @param manager
	 */
	public void setManager(boolean manager) {
		this.manager = manager;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * 
	 * @param mobilePhone
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getHomePhone() {
		return homePhone;
	}
	
	/**
	 * 
	 * @param homePhone
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getHireDate() {
		return hireDate;
	}
	
	/**
	 * 
	 * @param hireDate
	 */
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMaxHoursWeek() {
		return maxHoursWeek;
	}
	
	/**
	 * 
	 * @param maxHoursWeek
	 */
	public void setMaxHoursWeek(int maxHoursWeek) {
		this.maxHoursWeek = maxHoursWeek;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMaxDaysWeek() {
		return maxDaysWeek;
	}
	
	/**
	 * 
	 * @param maxDaysWeek
	 */
	public void setMaxDaysWeek(int maxDaysWeek) {
		this.maxDaysWeek = maxDaysWeek;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMaxHoursDay() {
		return maxHoursDay;
	}
	
	/**
	 * 
	 * @param maxHoursDay
	 */
	public void setMaxHoursDay(int maxHoursDay) {
		this.maxHoursDay = maxHoursDay;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAddress2() {
		return address2;
	}
	
	/**
	 * 
	 * @param address2
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * 
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * 
	 * @param comments
	 */
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
	
	/**
	 * @return the wage
	 */
	public double getWage() {
		return wage;
	}
	
	/**
	 * @param wage the wage to set
	 */
	public void setWage(double wage) {
		this.wage = wage;
	}
	
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}
